package team.justtag.server.token.service;

import org.jose4j.json.internal.json_simple.JSONObject;

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
		System.out.println(body);
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

	@SuppressWarnings("unchecked")
	@Override
	public TokenStatus verifyToken(String token, String aud) {
		DBObject basic = null;
		long expireTime;
		try{
			basic = mCollection.findOne(new BasicDBObject("token", token));
			expireTime = new Long(basic.get("exp").toString());
		}catch(NullPointerException e){
			return TokenStatus.notFoundToken;
		}
		switch(isExpiredToken(expireTime)){
			case tokenExpired:
				return TokenStatus.tokenExpired;
			case success:
				break;
			default:
				return TokenStatus.unknownError;
		}		
		switch(isUpdateToken(expireTime)){
			case tokenExpiringsoon:
				return TokenStatus.tokenExpiringsoon;
			case success:
				return TokenStatus.success;
			default:
				return TokenStatus.unknownError;
		}
	}

	@Override
	public TokenStatus deleteToken(String token) {
		if(mCollection.remove(new BasicDBObject("token", token)).getN()>0){
			return TokenStatus.success;
		}else{
			return TokenStatus.notFoundToken;
		}		
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

	@SuppressWarnings("unchecked")
	@Override
	public String verifyandRefresh(String token, String aud) {
		switch(verifyToken(token, aud)){
		case notFoundToken:
			return TokenStatus.notFoundToken.name();
		case success:
			return TokenStatus.success.name();
		case tokenExpired:
			JSONObject json= new JSONObject();
			json.put("token", "token");
			return json.toJSONString();
		case tokenExpiringsoon:
			return TokenStatus.notFoundToken.name();
		case tokenUpdateFail:
			return TokenStatus.tokenUpdateFail.name();
		case unknownError:
		default:
			return TokenStatus.unknownError.name();
			
		}
		
	}
}