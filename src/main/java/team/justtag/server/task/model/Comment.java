package team.justtag.server.task.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Comment {
	
	private String _id;
	private String user_id;
	private String contents;
	private String reg_date;
	
	public Comment(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		this.user_id = dbObject.getString("user_id");
		this.contents = dbObject.getString("contents");
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	

}
