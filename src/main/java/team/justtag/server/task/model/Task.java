package team.justtag.server.task.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Task {
	
	private String _id;
	
	// who
	private String requester_id;
	private String requester_group_id;
	private List<String> responser_id;
	private String responser_group_id;
	
	// when
	private String reg_date;
	private String dealine;
	private String start_date;
	private String end_date;
	
	// where
	private String target_store_id;
	
	// what
	private List<String> target_item_meta_id;
	private List<String> target_item_id;
	
	// how
	private String action_id;

	// why
	private String subject;
	private String description;
	// info
	private String state;
	// comments
	private List<Comment> comments;
	
	@SuppressWarnings("unchecked")
	public Task(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		
		this.requester_id = dbObject.getString("requester_id");
		this.requester_group_id = dbObject.getString("requester_group_id");
		this.responser_id = (List<String>) dbObject.get("responser_id");
		this.responser_group_id = dbObject.getString("responser_group_id");
		
		this.reg_date = dbObject.getString("reg_date");
		this.dealine = dbObject.getString("dealine");
		this.start_date = dbObject.getString("start_date");
		this.end_date = dbObject.getString("end_date");
		
		this.target_store_id = dbObject.getString("target_store_id");
		
		this.target_item_meta_id = (List<String>) dbObject.get("target_item_meta_id");
		this.target_item_id =(List<String>)  dbObject.get("target_item_id");
		
		this.action_id = dbObject.getString("action_id");
		
		this.subject = dbObject.getString("subject");
		this.state = dbObject.getString("state");
		
		this.comments = (List<Comment>) dbObject.get("comments"); // Test 해야함_150924_14:16
		
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getRequester_id() {
		return requester_id;
	}

	public void setRequester_id(String requester_id) {
		this.requester_id = requester_id;
	}

	public String getRequester_group_id() {
		return requester_group_id;
	}

	public void setRequester_group_id(String requester_group_id) {
		this.requester_group_id = requester_group_id;
	}

	public List<String> getResponser_id() {
		return responser_id;
	}

	public void setResponser_id(List<String> responser_id) {
		this.responser_id = responser_id;
	}

	public String getResponser_group_id() {
		return responser_group_id;
	}

	public void setResponser_group_id(String responser_group_id) {
		this.responser_group_id = responser_group_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getDealine() {
		return dealine;
	}

	public void setDealine(String dealine) {
		this.dealine = dealine;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getTarget_store_id() {
		return target_store_id;
	}

	public void setTarget_store_id(String target_store_id) {
		this.target_store_id = target_store_id;
	}

	public List<String> getTarget_item_meta_id() {
		return target_item_meta_id;
	}

	public void setTarget_item_meta_id(List<String> target_item_meta_id) {
		this.target_item_meta_id = target_item_meta_id;
	}

	public List<String> getTarget_item_id() {
		return target_item_id;
	}

	public void setTarget_item_id(List<String> target_item_id) {
		this.target_item_id = target_item_id;
	}

	public String getAction_id() {
		return action_id;
	}

	public void setAction_id(String action_id) {
		this.action_id = action_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}	
	

}
