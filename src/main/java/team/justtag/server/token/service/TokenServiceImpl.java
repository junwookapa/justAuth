package team.justtag.server.token.service;

import team.justtag.server.main.Config;
import team.justtag.server.main.Status.TokenStatus;
import team.justtag.server.security.AESToken;
import team.justtag.server.token.dao.TokenDao;
import team.justtag.server.token.dao.TokenDaoImpl;
import team.justtag.server.token.model.Token;
import team.justtag.server.util.RandomString;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class TokenServiceImpl implements TokenService {

	private final DBCollection mCollection;
	private final TokenDao mTokenDao;

	public TokenServiceImpl(DB db) {
		this.mCollection = db.getCollection("token");
		this.mTokenDao = new TokenDaoImpl(db);
	}

	@Override
	public String issueToken(String body, String aud) {
		Token token = new Gson().fromJson(body, Token.class);
		long nowTime = System.currentTimeMillis() / 1000;
		long exp = nowTime + new Long(Config.EXPIRED_TOKEN_TIME);
		token.setAud(aud);
		token.setExp(exp+"");
		token.setIat(nowTime+"");
		token.setIss(Config.APP_DNS);
		token.setAes_key(new RandomString(16).nextString());
		String tokenString = new AESToken().encodingToken(new Gson().toJson(token), token.getAes_key());
		token.setToken(tokenString);
		switch (mTokenDao.isUserExist(token.getUser_id())) {
		case duplicated:
			String objID = mTokenDao.getTokenIDByUserID(token.getUser_id());
			mTokenDao.updateToken(objID, token);
			break;
		case noproblem:
			mTokenDao.createToken(token);
			break;
		default:
			return TokenStatus.unknownError.name();
		}
		return tokenString;
	}

	@Override
	public String verifyToken(String token, String aud) {
		DBObject basic = mCollection.findOne(new BasicDBObject("token", token));
		long expireTime = new Long((long) basic.get("exp"));
		switch(isExpiredToken(expireTime)){
			case tokenExpired:
				return TokenStatus.tokenExpired.name();
			case success:
				break;
			default:
				return TokenStatus.unknownError.name();
		}		
		switch(isUpdateToken(expireTime)){
			case tokenExpiringsoon:
				return issueToken(token, aud);
			case success:
				return TokenStatus.success.name();
			default:
				return TokenStatus.unknownError.name();
		}
	}

	@Override
	public TokenStatus deleteToken(String token) {
		mCollection.remove(new BasicDBObject("token", token));
		return TokenStatus.success;
	}
	
	@Override
	public TokenStatus isUpdateToken(long expireTime) {
		// TODO Auto-generated method stub
		long nowTime = System.currentTimeMillis() / 1000;
		long remainTime = expireTime - nowTime;
		
		if (remainTime > new Long(Config.REFREASH_TOKEN_TIME)) {
			return TokenStatus.success; // 변경안해도 됨
		}else{
			return TokenStatus.tokenExpiringsoon; // 변경해야함
		}
	}
	
	@Override
	public TokenStatus isExpiredToken(long expireTime) {
		// TODO Auto-generated method stub
		long nowTime = System.currentTimeMillis() / 1000;
		long remainTime = expireTime - nowTime;
		
		if (remainTime > 0) {
			return TokenStatus.success; // 토큰 살아 있음
		}else{
			return TokenStatus.tokenExpired; // 토큰 죽음
		}
	}
}