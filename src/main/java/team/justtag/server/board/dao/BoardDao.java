package team.justtag.server.board.dao;

import java.util.List;

import team.justtag.server.board.model.Board;
import team.justtag.server.main.Status.DBStatus;

public interface BoardDao {
	public DBStatus create(Board board);
	public DBStatus update(String _id ,Board board);
	public DBStatus delete(String _id);
	public List<Board> getBoards();
	public Board getBoardByBoardID(String _id);
}
