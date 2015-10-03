package team.justtag.server.board.model;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Board {

    private String id;
    private String user_id;
    private String contents;
    private String place;
    private Date reg_date = new Date();

    public Board(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.user_id = dbObject.getString("user_id");
        this.contents = dbObject.getString("contents");
        this.place = dbObject.getString("place");
        this.reg_date = dbObject.getDate("reg_date");
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

}
