package team.justauth.server.board.service;

import java.util.Date;
import java.util.List;

import team.justauth.server.board.dao.BoardDao;
import team.justauth.server.board.dao.BoardDaoImpl;
import team.justauth.server.board.model.Board;
import team.justauth.server.security.AESToken;
import team.justauth.server.token.dao.TokenDao;
import team.justauth.server.token.dao.TokenDaoImpl;
import team.justauth.server.token.model.Token;

import com.google.gson.Gson;
import com.mongodb.DB;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class BoardServiceImpl implements BoardService{

	private final DB db;
	private final BoardDao mBoardDao;
	private final TokenDao mTokenDao;

	public BoardServiceImpl(DB db) {
		this.db = db;
		this.mBoardDao = new BoardDaoImpl(db);
		this.mTokenDao = new TokenDaoImpl(db);
	}

	public List<Board> findAll() {
		return mBoardDao.getBoards();
	}

	public void createNewContents(String body, String token) {
		System.out.println(token);
		Token tokenx =mTokenDao.getTokenByToken(token);
		String decodingToken = AESToken.decodingToken(token, tokenx.getAes_key());
		Token tokenObj = new Gson().fromJson(decodingToken, Token.class);
		Board board = new Gson().fromJson(body, Board.class);
		board.setPlace(tokenObj.getAud());
		board.setUser_id(tokenObj.getUser_id());
		board.setReg_date(new Date());
		mBoardDao.create(board);
	}

	public Board find(String id) {
		return mBoardDao.getBoardByBoardID(id);
	}

	public Board update(String boardID, String body) {
		Board board = new Gson().fromJson(body, Board.class);
		mBoardDao.update(boardID, board);
		return board;
	}
}
