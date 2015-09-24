package team.justtag.server.item.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class ItemGroup {
	private String _id;
	private String item_group_name; // 이름
	private String item_group_description; // 설명
	private String reg_date; // 등록일 
	private List<String> itemmetas; //아이템 메타들
	
	@SuppressWarnings("unchecked")
	public ItemGroup(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();		
		this.item_group_name = dbObject.getString("item_group_name");
		this.item_group_description = dbObject.getString("item_group_description");
		this.reg_date = dbObject.getString("reg_date");
		this.itemmetas = (List<String>) dbObject.get("itemmetas");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getItem_group_name() {
		return item_group_name;
	}

	public void setItem_group_name(String item_group_name) {
		this.item_group_name = item_group_name;
	}

	public String getItem_group_description() {
		return item_group_description;
	}

	public void setItem_group_description(String item_group_description) {
		this.item_group_description = item_group_description;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public List<String> getItemmetas() {
		return itemmetas;
	}

	public void setItemmetas(List<String> itemmetas) {
		this.itemmetas = itemmetas;
	}
	
}
