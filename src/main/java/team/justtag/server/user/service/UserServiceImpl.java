package team.justtag.server.user.service;

import java.util.Date;
import java.util.List;

import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.user.dao.UserDao;
import team.justtag.server.user.dao.UserDaoImpl;
import team.justtag.server.user.dao.UserGroupDao;
import team.justtag.server.user.dao.UserGroupDaoImpl;
import team.justtag.server.user.model.User;
import team.justtag.server.util.AESSecurity;
import team.justtag.server.util.JWESecurity;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoException;

public class UserServiceImpl implements UserService {
	private final UserDao mUserDao;
	private final UserGroupDao mUserGroupDao;
	private final JWESecurity mJWESecurity;
	private final AESSecurity mAESSecurity;

	public UserServiceImpl(DB db) {
		this.mUserDao = new UserDaoImpl(db);
		this.mUserGroupDao = new UserGroupDaoImpl(db);
		this.mJWESecurity = new JWESecurity();
		this.mAESSecurity = new AESSecurity();
	}

	@Override
	public UserStatus createUser(String body) {
		String decodedbody = mJWESecurity.decoding(body);
		User user = new Gson().fromJson(decodedbody, User.class);
		try{
			user.setReg_date(new Date().toString());
			mUserDao.createUser(user);
			mUserGroupDao.addUser(user.getGroup_id(), mUserDao.getObjIDByUserID(user.getUser_id()));
			return UserStatus.success;
		}catch(Exception e){
			return UserStatus.signFail;
		}
	}

	@Override
	public UserStatus deleteUser(String body) {
		try {
			String decodedbody = mJWESecurity.decoding(body);
			User user = new Gson().fromJson(decodedbody, User.class);
			mUserGroupDao.deleteUser(user.getGroup_name(), mUserDao.getObjIDByUserID(user.getUser_id()));
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
		try {
			String decodedbody = mJWESecurity.decoding(body);
			user = new Gson().fromJson(decodedbody, User.class);
			compareUser = mUserDao.getUserByUserID(user.getUser_id());
			decodedPassword = mAESSecurity.decoding(compareUser.getPassword());
		} catch (Exception e) {
			return UserStatus.unkwonError;
		}
		if (user.getPassword().equals(decodedPassword)) {
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

}
