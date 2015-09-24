package team.justtag.server.user.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Store {
	private String _id;
	private String store_name;
	private String store_address;
	private String store_tel;
	private String description;
	
	public Store(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.store_name = dbObject.getString("store_name");
		this.store_address = dbObject.getString("store_address");
		this.store_tel = dbObject.getString("store_tel");
		this.description = dbObject.getString("description");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_tel() {
		return store_tel;
	}

	public void setStore_tel(String store_tel) {
		this.store_tel = store_tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
