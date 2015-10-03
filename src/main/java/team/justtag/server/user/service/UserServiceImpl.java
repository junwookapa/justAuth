package team.justtag.server.user.service;

import java.util.Date;
import java.util.List;

import team.justtag.server.main.Config;
import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.main.Status.Role;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.security.AESSecurity;
import team.justtag.server.security.AESToken;
import team.justtag.server.user.dao.UserDao;
import team.justtag.server.user.dao.UserDaoImpl;
import team.justtag.server.user.model.User;
import team.justtag.server.util.RandomString;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class UserServiceImpl implements UserService {
	private final UserDao mUserDao;


	public UserServiceImpl(DB db) {
		this.mUserDao = new UserDaoImpl(db);
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
	public List<User> findAllUsers() {
		List<User> asd = mUserDao.getAllUsers();
		for(int i=0; i<asd.size(); i++)
			System.out.println(mUserDao.getAllUsers().get(i).get_id());
		return mUserDao.getAllUsers();
	}

	@Override
	public List<User> findAllUsersByUserGroup(String userGroupID) {
		return null;
	}

	@Override
	public UserStatus logout(String body) {
		// TODO Auto-generated method stub
		return null;
	}

}
