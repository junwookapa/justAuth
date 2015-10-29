package team.justauth.server.user.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class UserInfo {
	private String _id; // id
	private String user_id; // 사용자 id
	private String user_name; // 사용자 이름
	private String user_role; // 역할 admin, group master, ...
	private String user_email; // 이메일
	private String reg_date; // 생성일
	
	public UserInfo(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		this.user_id = dbObject.getString("user_id");
		this.user_name = dbObject.getString("user_name");
		this.user_role = dbObject.getString("user_role");
		this.user_email = dbObject.getString("user_email");
		this.reg_date = dbObject.getString("reg_date");
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}//ㅁ
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
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	

}
