package team.justtag.auth.user.vo;

import java.io.UncheckedIOException;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class UserGroup {
	private String _id;
	private String groupName;
	private List<User> users;
	
	@SuppressWarnings("unchecked")
	public UserGroup(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.groupName = dbObject.getString("groupName");
		try{
			this.users = (List<User>) dbObject.get("users");
		}catch(Exception e){
			
		}
	}
	
	
}