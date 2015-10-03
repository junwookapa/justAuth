package team.justtag.server.board.service;

import java.util.Date;
import java.util.List;

import team.justtag.server.board.dao.BoardDao;
import team.justtag.server.board.dao.BoardDaoImpl;
import team.justtag.server.board.model.Board;
import team.justtag.server.security.AESToken;
import team.justtag.server.token.dao.TokenDao;
import team.justtag.server.token.dao.TokenDaoImpl;
import team.justtag.server.token.model.Token;
import team.justtag.server.user.model.User;

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
		String decodingToken = new AESToken().decodingToken(token, tokenx.getAes_key());
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
