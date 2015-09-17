package team.justtag.auth.user.vo;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class User {
	private String _id; // Object id
	private String userID; // user id
	private String password; // user password
	private String role; // user role, admin or enduser
	private String email; // user email
	private String affiliation; // 소속
	private String createOn; // 생성일
	
	public User(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.userID = dbObject.getString("userID");
		this.password = dbObject.getString("password");
		this.role = dbObject.getString("role");
		this.email = dbObject.getString("email");
		this.affiliation = dbObject.getString("affiliation");
		this.createOn = dbObject.getString("createOn");
	}

	public String get_id() {
		return _id;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public String getCreateOn() {
		return createOn;
	}
	


}
