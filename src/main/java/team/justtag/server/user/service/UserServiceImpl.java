package team.justtag.server.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.user.model.User;
import team.justtag.server.util.AESSecurity;
import team.justtag.server.util.JWESecurity;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class UserServiceImpl implements UserService {
	private final DBCollection mCollection;
	private final JWESecurity mJWESecurity;
	private final AESSecurity mAESSecurity;

	public UserServiceImpl(DB db) {
		this.mCollection = db.getCollection("user");
		this.mJWESecurity = new JWESecurity();
		this.mAESSecurity = new AESSecurity();
	}

	@Override
	public UserStatus createUser(String body) {
		return null;
/*		String decodedbody = mJWESecurity.decoding(body);
		User user = new Gson().fromJson(decodedbody, User.class);
		if (mCollection.findOne(new BasicDBObject("userID", user.getUserID())) == null) {
			mCollection.insert(new BasicDBObject("userID", user.getUserID())
					.append("password",
							mAESSecurity.encoding(user.getPassword()))
					.append("role", user.getRole())
					.append("email", user.getEmail())
					.append("group", user.getGroup())
					.append("createdOn", new Date()));
			
			return UserStatus.success;
		} else {
			return UserStatus.duplicateID;
		}*/
	}

	@Override
	public UserStatus deleteUser(String body) {
		// TODO Auto-generated method stub
		try {
	/*		String decodedbody = mJWESecurity.decoding(body);
			User user = new Gson().fromJson(decodedbody, User.class);
			mCollection.remove(new BasicDBObject("userID", user.getUserID()));*/
			
			

		} catch (Exception e) {

		}
		return UserStatus.success;
	}

	@Override
	public UserStatus login(String body) {
		// TODO Auto-generated method stub
		User user = null;
		User compareUser = null;
		String decodedPassword = null;
		try {
/*			String decodedbody = mJWESecurity.decoding(body);
			user = new Gson().fromJson(decodedbody, User.class);
			compareUser = findUserbyUserID(user.getUserID());*/
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserbyUserID(String userID) {
		try {
			return new User(
					(BasicDBObject) mCollection.findOne(new BasicDBObject(
							"userID", userID)));
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
		List<User> users = new ArrayList<>();
		DBCursor dbObjects = mCollection.find();
		while (dbObjects.hasNext()) {
			DBObject dbObject = dbObjects.next();
			users.add(new User((BasicDBObject) dbObject));
		}
		return users;
	}

	@Override
	public List<User> findAllUsersByUserGroup(String userGroupID) {
		// TODO Auto-generated method stub
		return null;
	}

}
