package team.justtag.server.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.User;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserDaoImpl implements UserDao{
	
	private DBCollection mCollection;
	public UserDaoImpl(DB db){
		this.mCollection = db.getCollection("user");
	}
	@Override
	public DBStatus createUser(User user) {
		try{
			mCollection.insert(new BasicDBObject("userID", user.getUser_id())
					.append("user_id", user.getUser_id())
					.append("user_name", user.getUser_name())
					.append("user_group_id", user.getUser_group_id())
					.append("password", user.getPassword())
					.append("role", user.getRole())
					.append("email", user.getEmail())
					.append("store_id", user.getStore_id()));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.insertFail;
		}
	}

	@Override
	public DBStatus updateUser(String _id, User user) {
		try{
			mCollection.update(
					new BasicDBObject("_id", new ObjectId(_id))
					, new BasicDBObject("userID", user.getUser_id())
					.append("user_id", user.getUser_id())
					.append("user_name", user.getUser_name())
					.append("user_group_id", user.getUser_group_id())
					.append("password", user.getPassword())
					.append("role", user.getRole())
					.append("email", user.getEmail())
					.append("store_id", user.getStore_id()));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.updateFail;
		}
	}
	@Override
	public DBStatus deleteUser(String _id) {
		try{
			mCollection.remove(new BasicDBObject("_id", new ObjectId(_id)));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.deleteFail;
		}
	}
	@Override
	public User findbyObjectID(String _id) {
		try{
			 return new User((BasicDBObject) mCollection.findOne(new BasicDBObject("_id", new ObjectId(_id))));
		}catch(Exception e){
			return null;
		}
	}
	@Override
	public User findbyUserID(String user_id) {
		// TODO Auto-generated method stub
		try{
			 return new User((BasicDBObject) mCollection.findOne(new BasicDBObject("user_id", new ObjectId(user_id))));
		}catch(Exception e){
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
	public List<User> findAllUsersByUserGroupName(String userGroupName) {
		List<User> users = new ArrayList<>();
        DBCursor dbObjects = mCollection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            users.add(new User((BasicDBObject) dbObject));
        }
        return users;
	}
	

}
