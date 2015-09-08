package team.justtag.server.login.vo;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class User {
	private String id;
	private String user;
	private String password;
	private String createOn;

	public User(BasicDBObject dbObject) {
		this.id = ((ObjectId) dbObject.get("_id")).toString();
		this.user = dbObject.getString("user");
		this.password = dbObject.getString("password");
		this.createOn = dbObject.getString("createOn");
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getCreateOn() {
		return createOn;
	}
	

}
