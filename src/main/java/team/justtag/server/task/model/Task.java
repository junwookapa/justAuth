package team.justtag.server.task.model;

import java.util.List;

public class Task {
	
	private String _id;
	private String name;

	private String requester_id;
	private String requester_group_id;
	private String responser_id;
	private String responser_group_id;
	
	private String reg_date;
	private String dealine;
	private String start_date;
	private String end_date;
	
	private String target_store_id;
	
	private String target_item_meta_id;
	private String target_item_id;
	
	private String action_id;
	
	private String state;
	private List<Comment> comments;

}
