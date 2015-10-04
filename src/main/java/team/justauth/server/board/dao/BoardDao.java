package team.justauth.server.board.dao;

import java.util.List;

import team.justauth.server.board.model.Board;
import team.justauth.server.main.Status.DBStatus;

public interface BoardDao {
	public DBStatus create(Board board);
	public DBStatus update(String _id ,Board board);
	public DBStatus delete(String _id);
	public List<Board> getBoards();
	public Board getBoardByBoardID(String _id);
}
