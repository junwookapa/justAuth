package team.justtag.server.token.service;

import team.justtag.server.main.Config;
import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.main.Status.TokenStatus;
import team.justtag.server.security.AESToken;
import team.justtag.server.security.AESSecurity;
import team.justtag.server.security.JWEwithAES;
import team.justtag.server.token.dao.TokenDao;
import team.justtag.server.token.dao.TokenDaoImpl;
import team.justtag.server.token.model.Token;
import team.justtag.server.user.dao.UserDao;
import team.justtag.server.user.dao.UserDaoImpl;
import team.justtag.server.util.RandomString;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class TokenServiceImpl implements TokenService {

	private final DBCollection mCollection;
	private final JWEwithAES mJWESecurity;
	private final AESSecurity mAESSecurity;
	private final TokenDao mTokenDao;

	public TokenServiceImpl(DB db) {
		this.mCollection = db.getCollection("token");
		this.mJWESecurity = new JWEwithAES();
		this.mAESSecurity = new AESSecurity();
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
			break;
		}
		return tokenString;
		
	}

	@Override
	public String updateToken(String strToken, String aud) {
		// TODO Auto-generated method stub
		//시간 확인용
		long nowTime = System.currentTimeMillis() / 1000;
		long exp = nowTime + new Long(Config.EXPIRED_TOKEN_TIME);
		Token token  = new Gson().fromJson(mJWESecurity.decoding(strToken), Token.class);
		token.setAud(aud);
		token.setExp(exp+"");
		token.setIat(nowTime+"");
		token.setIss(Config.APP_DNS);
	//	String tokenString = mJWESecurity.encoding(new Gson().toJson(token));
		BasicDBObject searchQry = new BasicDBObject("token", strToken);
		BasicDBObject changeDoc = new BasicDBObject();
	/*	changeDoc.append("$set", new BasicDBObject("token", tokenString)
		.append("userID", token.getUser_id())
		.append("exp", token.getExp()).append("iat", token.getIat())
		.append("iss", token.getIss()).append("aud", token.getAud()));*/
		
		//http://wannastop.tistory.com/486
		if(mCollection.update(searchQry, changeDoc).getN() > 0){
		//	return tokenString;
		}else{
		//	return TokenStatus.tokenUpdateFail.name();
		}
		return aud;
	}

	@Override
	public String verifyToken(String token, String aud) {
		// TODO Auto-generated method stub
		DBObject basic = mCollection.findOne(new BasicDBObject("token", token));	
		if(isUpdateToken(new Long(basic.get("exp").toString())).equals(TokenStatus.tokenExpiringsoon)){
			return issueToken(token, aud); //토큰 
		}else{
			return TokenStatus.success.name();
		}

	}

	@Override
	public TokenStatus deleteToken(String token) {//예외처리 안함
		// TODO Auto-generated method stub
		mCollection.remove(new BasicDBObject("token", token));
		return TokenStatus.success;
	}

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
