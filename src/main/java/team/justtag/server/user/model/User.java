package team.justtag.server.user.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class User {
	private String _id; // id
	private String user_id; // 사용자 id
	private String user_name; // 사용자 이름
	private String group_id; // 그룹 아이디
	private String group_name; // 그룹 이름, 비정규화
	private String password; // 비밀번호
	private String role; // 역할 admin, group master, ...
	private String email; // 이메일
	private String store_id; // 가게 아이디
	private String reg_date; // 생성일
	
	public User(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.user_id = dbObject.getString("user_id");
		this.user_name = dbObject.getString("user_name");
		this.group_id = dbObject.getString("group_id");
		this.group_name = dbObject.getString("group_name");
		this.password = dbObject.getString("password");
		this.role = dbObject.getString("role");
		this.email = dbObject.getString("email");
		this.store_id = dbObject.getString("store_id");
		this.reg_date = dbObject.getString("reg_date");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	
}
