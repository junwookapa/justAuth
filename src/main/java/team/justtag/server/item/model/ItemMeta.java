package team.justtag.server.item.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class ItemMeta {
	private String _id;
	
	private String item_meta_name;
	private String item_meta_description;
	
	private String item_meta_unit_measure;
	
	private String item_group_id; // 비정규화
	private String item_group_name; // 비정규화
	
	private String reg_date;
	
	private List<String> items;
	
	@SuppressWarnings("unchecked")
	public ItemMeta(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.item_meta_name = dbObject.getString("item_meta_name");
		this.item_meta_description = dbObject.getString("item_meta_description");
		this.item_meta_unit_measure = dbObject.getString("item_meta_unit_measure");
		this.item_group_id = dbObject.getString("item_group_id");
		this.item_group_name = dbObject.getString("item_group_name");
		this.reg_date = dbObject.getString("reg_date");
		this.items = (List<String>) dbObject.get("items");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getItem_meta_name() {
		return item_meta_name;
	}

	public void setItem_meta_name(String item_meta_name) {
		this.item_meta_name = item_meta_name;
	}

	public String getItem_meta_description() {
		return item_meta_description;
	}

	public void setItem_meta_description(String item_meta_description) {
		this.item_meta_description = item_meta_description;
	}

	public String getItem_meta_unit_measure() {
		return item_meta_unit_measure;
	}

	public void setItem_meta_unit_measure(String item_meta_unit_measure) {
		this.item_meta_unit_measure = item_meta_unit_measure;
	}

	public String getItem_group_id() {
		return item_group_id;
	}

	public void setItem_group_id(String item_group_id) {
		this.item_group_id = item_group_id;
	}

	public String getItem_group_name() {
		return item_group_name;
	}

	public void setItem_group_name(String item_group_name) {
		this.item_group_name = item_group_name;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
	
	
}