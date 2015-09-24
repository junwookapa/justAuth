package team.justtag.server.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.user.model.UserGroup;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserGroupDaoImpl implements UserGroupDao{

	private final DBCollection mCollection;
	public UserGroupDaoImpl(DB db){
		this.mCollection = db.getCollection("usergroup");
	}
	
	@Override
	public DBStatus createUserGroup(UserGroup user) {
		try{
			mCollection.insert(new BasicDBObject()
					.append("user_group_name", user.getUser_group_name())
					.append("user_group_description", user.getUser_group_description())
					.append("reg_date", user.getReg_date())
					.append("users", user.getUsers()));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.insertFail;
		}
	}
	
	@Override
	public DBStatus updateUserGroup(String _id, UserGroup user) {
		try{
			mCollection.update(
					new BasicDBObject("_id", new ObjectId(_id))
					, new BasicDBObject("$set", new BasicDBObject()
					.append("user_group_name", user.getUser_group_name())
					.append("user_group_description", user.getUser_group_description())
					.append("reg_date", user.getReg_date())
					.append("users", user.getUsers())));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.updateFail;
		}
	}

	@Override
	public DBStatus deleteUserGroup(String _id) {
		try{
			mCollection.remove(new BasicDBObject("_id", new ObjectId(_id)));			
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.deleteFail;
		}
	}

	@Override
	public UserGroup getUserGroupByObjectID(String _id) {
		try{
			 return new UserGroup((BasicDBObject) mCollection.findOne(new BasicDBObject("_id", new ObjectId(_id))));
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public UserGroup getUserGroupByUserGroupName(String user_group_name) {
		try{
			 return new UserGroup((BasicDBObject) mCollection.findOne(new BasicDBObject("user_group_name", user_group_name)));
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getObjIDByUserGroupName(String user_group_name) {
		try{
			 return new UserGroup((BasicDBObject) mCollection.findOne(new BasicDBObject("user_group_name", user_group_name))).get_id();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List<UserGroup> getAllUserGroups() {
		List<UserGroup> userGroups = new ArrayList<>();
        DBCursor dbObjects = mCollection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            userGroups.add(new UserGroup((BasicDBObject) dbObject));
        }
        return userGroups;
	}

	@Override
	public DBStatus addUser(String user_group_name, String userObjid) {
		try{
			mCollection.update(
					new BasicDBObject("user_group_name", user_group_name)
					, new BasicDBObject("$addToSet", new BasicDBObject("users", new ObjectId(userObjid))));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.updateFail;
		}
	}
	@Override
	public DBStatus deleteUser(String user_group_name, String userObjid) {
		try{
			mCollection.update(
					new BasicDBObject("user_group_name", user_group_name)
					, new BasicDBObject("$pull", new BasicDBObject("users", new ObjectId(userObjid))));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.updateFail;
		}
	}

}
