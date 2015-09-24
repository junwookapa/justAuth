package team.justtag.server.user.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class UserGroup {
	private String _id;
	private String user_group_name;
	private String user_group_description;
	private String reg_date;
	private List<String> users;

	@SuppressWarnings("unchecked")
	public UserGroup(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		this.user_group_name = dbObject.getString("user_group_name");
		this.user_group_description = dbObject.getString("user_group_description");
		this.reg_date = dbObject.getString("reg_date");
		this.users = (List<String>) dbObject.get("users");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUser_group_name() {
		return user_group_name;
	}

	public void setUser_group_name(String user_group_name) {
		this.user_group_name = user_group_name;
	}

	public String getUser_group_description() {
		return user_group_description;
	}

	public void setUser_group_description(String user_group_description) {
		this.user_group_description = user_group_description;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}


}