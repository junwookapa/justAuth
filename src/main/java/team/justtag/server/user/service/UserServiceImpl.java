package team.justtag.server.user.service;

import java.util.Date;
import java.util.List;

import team.justtag.server.main.Config;
import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.main.Status.Role;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.security.AESSecurity;
import team.justtag.server.security.AESToken;
import team.justtag.server.token.dao.TokenDao;
import team.justtag.server.token.dao.TokenDaoImpl;
import team.justtag.server.token.model.Token;
import team.justtag.server.user.dao.UserDao;
import team.justtag.server.user.dao.UserDaoImpl;
import team.justtag.server.user.model.User;
import team.justtag.server.user.model.UserInfo;
import team.justtag.server.util.RandomString;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class UserServiceImpl implements UserService {
	private final UserDao mUserDao;
	private final TokenDao mTokenDao;


	public UserServiceImpl(DB db) {
		this.mUserDao = new UserDaoImpl(db);
		this.mTokenDao = new TokenDaoImpl(db);
		createAdmin();
	}
	private void createAdmin(){
		User user = new User();
		user.setUser_id("admin");
		user.setUser_name("admin");
		user.setUser_password("admin");
		user.setUser_email("admin@justtag.com");
		user.setUser_role(Role.admin.name());
		createUser(new Gson().toJson(user));
	}
	
	@Override
	public UserStatus createUser(String body) {
		try{
			User user = new Gson().fromJson(body, User.class);
			mUserDao.isUserExist(user.getUser_id());
			if(!mUserDao.isUserExist(user.getUser_id()).equals(DBStatus.success)){
				return UserStatus.duplicatedID;
			}
			user.setReg_date(new Date().toString());
			String tokenString = new RandomString(Config.AES_USER_PASSWORD_LENGTH).nextString();
			user.setAes_key(tokenString);
			String encodingUserpassword = new AESSecurity().encoding(user.getUser_password(), user.getAes_key());
			user.setUser_password(encodingUserpassword);
			switch(mUserDao.createUser(user)){
			default:
				return UserStatus.unkwonError;
			case insertFail:
				return UserStatus.signFail;
			case success:
				return UserStatus.success;
			}
		}catch(Exception e){
			return UserStatus.unkwonError;
		}
	}

	@Override
	public UserStatus deleteUser(String body) {
		try {
			User user = new Gson().fromJson(body, User.class);
			mUserDao.deleteUser(user.getUser_id());
			return UserStatus.success;
		} catch (Exception e) {
			return UserStatus.unkwonError;
		}
	}

	@Override
	public UserStatus login(String body) {
		try{
			User user = new Gson().fromJson(body, User.class);
			User compareUser = mUserDao.getUserByUserID(user.getUser_id());
			String aes_key = mUserDao.getAES_KEY(compareUser.get_id());	
			String decodedPassword = new AESSecurity().decoding(compareUser.getUser_password(), aes_key);
			if (user.getUser_password().equals(decodedPassword)) {
				return UserStatus.success;
			} else {
				return UserStatus.wrongPassword;
			}
		}catch(Exception e){
			return UserStatus.unkwonError;
		}
	}

	@Override
	public UserStatus updateUser(String body) {
		return null;
	}

	@Override
	public User findUserbyUserID(String userID) {
		try {
			return mUserDao.getUserByUserID(userID);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (MongoException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserInfo> findAllUsers(String token) {
		Token tokenx =mTokenDao.getTokenByToken(token);
		String test = new AESToken().decodingToken(token, tokenx.getAes_key());
		User user = new Gson().fromJson(test, User.class);
		String role = mUserDao.getUserByUserID(user.getUser_id()).getUser_role();
		try{
			if(role.equals(Role.admin.name())){
				List<UserInfo> users = mUserDao.getAllUsers();
				return users;
			}else{
				return null;
			}
		}catch(NullPointerException e){
			return null;
		}
	}

	@Override
	public UserStatus logout(String body) {
		// TODO Auto-generated method stub
		return null;
	}

}
