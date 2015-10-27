package team.justauth.server.user.service;

import java.util.Date;
import java.util.List;

import team.justauth.server.main.Config;
import team.justauth.server.main.Status.DBStatus;
import team.justauth.server.main.Status.Role;
import team.justauth.server.main.Status.UserStatus;
import team.justauth.server.security.AESSecurity;
import team.justauth.server.user.dao.UserDao;
import team.justauth.server.user.dao.UserDaoImpl;
import team.justauth.server.user.model.User;
import team.justauth.server.user.model.UserInfo;
import team.justauth.server.util.Log;
import team.justauth.server.util.RandomString;

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
		user.setUser_email("admin@justauth.com");
		user.setUser_role(Role.admin.name());
		createUser(new Gson().toJson(user));
		Log.writeLog("[유저생성]"+user.getUser_id());
	}
	
	@Override
	public UserStatus createUser(String body) {
		try{
			User user = new Gson().fromJson(body, User.class);
			if(!mUserDao.isUserExist(user.getUser_id()).equals(DBStatus.success)){
				return UserStatus.duplicatedID;
			}
			user.setReg_date(new Date().toString());
			String tokenString = new RandomString(Config.AES_USER_PASSWORD_LENGTH).nextString();
			user.setAes_key(tokenString);
			String encodingUserpassword = AESSecurity.encoding(user.getUser_password(), user.getAes_key());
			user.setUser_password(encodingUserpassword);
			Log.writeLog("[유저생성]"+user.getUser_id());
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
	public UserStatus deleteUser(String userID) {
		try {
			String userObjId = mUserDao.getUserByUserID(userID).get_id();
			mUserDao.deleteUser(userObjId);
			Log.writeLog("[회원탈퇴]"+userID);
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
			String decodedPassword = AESSecurity.decoding(compareUser.getUser_password(), aes_key);
			if (user.getUser_password().equals(decodedPassword)) {
				Log.writeLog("[로그인]"+user.getUser_id());
				return UserStatus.success;
			} else {
				return UserStatus.wrongPassword;
			}
		}catch(Exception e){
			return UserStatus.unkwonError;
		}
	}

	@Override
	public UserStatus updateUser(String body, String userID) {
		User user = new Gson().fromJson(body, User.class);
		User oldUSer = mUserDao.getUserByUserID(user.getUser_id());
		System.out.println(user.getUser_password()+ oldUSer.getAes_key());
		String tokenString = new RandomString(Config.AES_USER_PASSWORD_LENGTH).nextString();
		user.setAes_key(tokenString);
		String encodingUserpassword = AESSecurity.encoding(user.getUser_password(), user.getAes_key());
		user.setUser_password(encodingUserpassword);
		
		switch(mUserDao.updateUser(oldUSer.get_id(), user)){
		default:
		case updateFail:
			return UserStatus.unkwonError;
		case success:
			Log.writeLog("[회원정보 수정]"+user.getUser_id());
			return UserStatus.success;
		}
	}

	@Override
	public UserInfo getUserInfoByUserID(String userID) {
		try {
			
			return mUserDao.getUserInfoByUserID(userID);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (MongoException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserInfo> findAllUsers(String token, String userID) {

		String role = mUserDao.getUserByUserID(userID).getUser_role();
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
