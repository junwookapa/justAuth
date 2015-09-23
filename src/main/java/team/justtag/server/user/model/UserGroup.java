package team.justtag.server.user.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class UserGroup {
	private String _id;
	private String group_name;
	private String description;
	private String reg_date;
	private List<String> users;
	
	@SuppressWarnings("unchecked")
	public UserGroup(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.group_name = dbObject.getString("group_name");
		this.description = dbObject.getString("description");
		this.reg_date = dbObject.getString("reg_date");
		this.users = (List<String>)dbObject.get("users");
		}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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