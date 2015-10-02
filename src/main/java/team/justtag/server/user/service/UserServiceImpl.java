package team.justtag.server.user.service;

import java.util.Date;
import java.util.List;

import team.justtag.server.main.Config;
import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.main.Status.Role;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.security.AESSecurity;
import team.justtag.server.security.JWEwithAES;
import team.justtag.server.user.dao.UserDao;
import team.justtag.server.user.dao.UserDaoImpl;
import team.justtag.server.user.model.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class UserServiceImpl implements UserService {
	private final UserDao mUserDao;
	private final JWEwithAES mJWESecurity;
	private final AESSecurity mAESSecurity;

	public UserServiceImpl(DB db) {
		this.mUserDao = new UserDaoImpl(db);
		this.mJWESecurity = new JWEwithAES();
		this.mAESSecurity = new AESSecurity();
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
				return UserStatus.duplicateID;
			}
			user.setReg_date(new Date().toString());
			String encodingUserpassword = mAESSecurity.encoding(user.getUser_password(), Config.AES_KEY);
			user.setUser_password(encodingUserpassword);
			String asd =mUserDao.createUser(user).toString();
			return UserStatus.success;
		}catch(Exception e){
			System.out.println("서비스에러");
			System.out.println(e.getMessage());
			return UserStatus.signFail;
		}
	}

	@Override
	public UserStatus deleteUser(String body) {
		try {
			String decodedbody = mJWESecurity.decoding(body);
			User user = new Gson().fromJson(decodedbody, User.class);
			mUserDao.deleteUser(user.getUser_id());
			return UserStatus.success;
		} catch (Exception e) {
			return UserStatus.unkwonError;
		}
	}

	@Override
	public UserStatus login(String body) {
		User user = null;
		User compareUser = null;
		String decodedPassword = null;
		System.out.println(body);
		try {			
			user = new Gson().fromJson(body, User.class);
			compareUser = mUserDao.getUserByUserID(user.getUser_id());
			decodedPassword = mAESSecurity.decoding(compareUser.getUser_password(), Config.AES_KEY);
		} catch (Exception e) {
			
			return UserStatus.unkwonError;
		}
		if (user.getUser_password().equals(decodedPassword)) {
			return UserStatus.success;
		} else {
			return UserStatus.wrongPassword;
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
