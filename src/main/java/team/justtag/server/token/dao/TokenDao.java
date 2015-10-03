package team.justtag.server.token.dao;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.token.model.Token;

public interface TokenDao {
	public DBStatus createToken(Token token); // 유저 생성
	public DBStatus updateToken(String _id, Token Token); // 유저 변경
	public DBStatus deleteToken(String _id); // 유저 삭제
	
	public Token getTokenByToken(String token); // 오브젝트 아이디에 의한 유저 객체 검색
	public Token getTokenByUserID(String user_id); // 유저 아이디에 의한 유저 객체 검색
	public String getTokenIDByUserID(String user_id);
	public String getTokenIDByToken(String token);
	public DBStatus isUserExist(String user_id);
	
}
