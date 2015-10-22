package team.justauth.server.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import team.justauth.server.board.model.Board;
import team.justauth.server.main.Status.DBStatus;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class BoardDaoImpl implements BoardDao{
	private final DBCollection mCollection;
	public BoardDaoImpl(DB db){
		this.mCollection = db.getCollection("board");
	}

	@Override
	public DBStatus create(Board board) {
		try{
			mCollection.insert(new BasicDBObject()
					.append("user_id", board.getUser_id())
					.append("contents", board.getContents())
					.append("place", board.getPlace())
					.append("reg_date", board.getReg_date()));
			return DBStatus.success;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return DBStatus.insertFail;
		}
	}


	@Override
	public DBStatus delete(String _id) {
			try{
				mCollection.remove((new BasicDBObject("_id", new ObjectId(_id))));
				return DBStatus.success;
			}catch(Exception e){
				return DBStatus.deleteFail;
			}
		
	}

	@Override
	public List<Board> getBoards() {
		 List<Board> boards = new ArrayList<>();
	        DBCursor dbObjects = mCollection.find();
	        while (dbObjects.hasNext()) {
	            DBObject dbObject = dbObjects.next();
	            boards.add(new Board((BasicDBObject) dbObject));
	        }
	        return boards;
	}

	@Override
	public Board getBoardByBoardID(String _id) {
		return new Board((BasicDBObject) mCollection.findOne(new BasicDBObject("_id", new ObjectId(_id))));
	}

	@Override
	public DBStatus update(String _id, Board board) {
		try{
			mCollection.update(
					new BasicDBObject("_id", new ObjectId(_id))
					, new BasicDBObject("$set", new BasicDBObject()
					.append("user_id", board.getUser_id())
					.append("contents", board.getContents())
					.append("place", board.getPlace())
					.append("reg_time", board.getReg_date())));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.updateFail;
		}
	}

	
	

}
