package team.justtag.server.user.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class User {
	private String _id; // id
	private String user_id; // 사용자 id
	private String user_name; // 사용자 이름
	private String user_group_id; // 소속
	private String password; // 비밀번호
	private String role; // 역할 admin, group master, ...
	private String email; // 이메일
	private String store_id; // 가게 아이디
	private String reg_date; // 생성일
	
	public User(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.user_id = dbObject.getString("user_id");
		this.user_name = dbObject.getString("user_name");
		this.user_group_id = dbObject.getString("user_group_id");
		this.password = dbObject.getString("password");
		this.role = dbObject.getString("role");
		this.email = dbObject.getString("email");
		this.store_id = dbObject.getString("store_id");
		this.reg_date = dbObject.getString("reg_date");
	}

	public String get_id() {
		return _id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_group_id() {
		return user_group_id;
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

	public String getStore_id() {
		return store_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	
}
