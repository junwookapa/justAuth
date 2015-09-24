package team.justtag.server.item.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Item {
	private String _id; // objectID
	private String item_id; // itemID;

	private String item_group_id; // 아이템 그룹 아이디, 비정규화
	private String item_group_name; // 아이템 그룹 이름, 비정규화

	private String item_meta_id; // 아이템 메타 이이디, 비정규화
	private String item_meta_name; // 아이템 메타 이름, 비정규화

	private String item_meta_unit_measure; // 비정규화
	private String item_price; // 가격
	private String item_count; // 숫자

	private String store_id; // 스토어 아이디
	private String regDate; // 등록일

	public Item(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		this.item_id = dbObject.getString("item_id");

		this.item_group_id = dbObject.getString("item_group_id");
		this.item_group_name = dbObject.getString("item_group_name");

		this.item_meta_id = dbObject.getString("item_meta_id");
		this.item_meta_name = dbObject.getString("item_meta_name");

		this.item_meta_unit_measure = dbObject
				.getString("item_meta_unit_measure");
		this.item_price = dbObject.getString("item_price");
		this.item_count = dbObject.getString("item_count");

		this.store_id = dbObject.getString("store_id");
		this.regDate = dbObject.getString("regDate");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
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

	public String getItem_meta_id() {
		return item_meta_id;
	}

	public void setItem_meta_id(String item_meta_id) {
		this.item_meta_id = item_meta_id;
	}

	public String getItem_meta_name() {
		return item_meta_name;
	}

	public void setItem_meta_name(String item_meta_name) {
		this.item_meta_name = item_meta_name;
	}

	public String getItem_meta_unit_measure() {
		return item_meta_unit_measure;
	}

	public void setItem_meta_unit_measure(String item_meta_unit_measure) {
		this.item_meta_unit_measure = item_meta_unit_measure;
	}

	public String getItem_price() {
		return item_price;
	}

	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	public String getItem_count() {
		return item_count;
	}

	public void setItem_count(String item_count) {
		this.item_count = item_count;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
