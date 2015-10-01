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
	
	private final DBCollection mCollection;
	public UserDaoImpl(DB db){
		this.mCollection = db.getCollection("user");
	}
	@Override
	public DBStatus createUser(User user) {
		try{
			mCollection.insert(new BasicDBObject()
					.append("user_id", user.getUser_id())
					.append("user_name", user.getUser_name())
					/*.append("user_group_id", new ObjectId(user.getUser_group_id()))*/
					.append("user_group_name", user.getUser_group_name())
					.append("user_password", user.getUser_password())
					.append("user_role", user.getUser_role())
					.append("user_email", user.getUser_email())
					.append("store_id", user.getStore_id())
					.append("reg_date", user.getReg_date()));
			return DBStatus.success;
		}catch(Exception e){
			System.out.println("다오에러");
			System.out.println(e.getMessage());
			return DBStatus.insertFail;
		}
	}

	@Override
	public DBStatus updateUser(String _id, User user) {
		try{
			mCollection.update(
					new BasicDBObject("_id", new ObjectId(_id))
					, new BasicDBObject("$set", new BasicDBObject()
					.append("user_id", user.getUser_id())
					.append("user_name", user.getUser_name())
					.append("user_group_id", new ObjectId(user.getUser_group_id()))
					.append("user_group_name", user.getUser_group_name())
					.append("user_password", user.getUser_password())
					.append("user_role", user.getUser_role())
					.append("user_email", user.getUser_email())
					.append("store_id", user.getStore_id())
					.append("reg_date", user.getReg_date())));
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
	public User getUserByObjectID(String _id) {
		try{
			 return new User((BasicDBObject) mCollection.findOne(new BasicDBObject("_id", new ObjectId(_id))));
		}catch(Exception e){
			return null;
		}
	}
	@Override
	public User getUserByUserID(String user_id) {
		try{
			 return new User((BasicDBObject) mCollection.findOne(new BasicDBObject("user_id", user_id)));
		}catch(Exception e){
			return null;
		}
	}
	@Override
	public String getObjIDByUserID(String user_id) {
		try{
			 return new User((BasicDBObject) mCollection.findOne(new BasicDBObject("user_id", user_id))).get_id();
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public List<User> getAllUsers() {
		 List<User> users = new ArrayList<>();
	        DBCursor dbObjects = mCollection.find();
	        while (dbObjects.hasNext()) {
	            DBObject dbObject = dbObjects.next();
	            users.add(new User((BasicDBObject) dbObject));
	        }
	        return users;
	}
	
	@Override
	public List<User> getUsersByUserGroupID(String user_group_id) {
		List<User> users = new ArrayList<>();
        DBCursor dbObjects = mCollection.find(new BasicDBObject("user_group_id", new ObjectId(user_group_id)));
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            users.add(new User((BasicDBObject) dbObject));
        }
        return users;
	}
	@Override
	public DBStatus isUserExist(String user_id) {
		try{
			if(mCollection.findOne(new BasicDBObject("user_id", user_id)).isPartialObject() != true){
				return DBStatus.insertFail;
			}else{
				return DBStatus.success;
			}
		}catch(Exception e){
			return null;
		}
		
	}
	
	

}
