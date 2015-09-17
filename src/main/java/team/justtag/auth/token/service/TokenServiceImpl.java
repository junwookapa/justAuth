package team.justtag.auth.token.service;

import team.justtag.auth.token.vo.Token;
import team.justtag.server.main.Config;
import team.justtag.server.main.Status.TokenStatus;
import team.justtag.server.util.AESSecurity;
import team.justtag.server.util.JWESecurity;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class TokenServiceImpl implements TokenService {

	private final DBCollection mCollection;
	private final JWESecurity mJWESecurity;
	private final AESSecurity mAESSecurity;

	public TokenServiceImpl(DB db) {
		this.mCollection = db.getCollection("token");
		this.mJWESecurity = new JWESecurity();
		this.mAESSecurity = new AESSecurity();
	}

	@Override
	public String issueToken(String body, String aud) {
		String decodedbody = mJWESecurity.decoding(body);
		Token token = new Gson().fromJson(decodedbody, Token.class);
		long nowTime = System.currentTimeMillis() / 1000;
		long exp = nowTime + new Long(Config.EXPIRED_TOKEN_TIME);
		token.setAud(aud);
		token.setExp(exp+"");
		token.setIat(nowTime+"");
		token.setIss(Config.APP_DNS);
		String tokenString = mJWESecurity.encoding(new Gson().toJson(token));
		isUserExist(token.getUserID());
		mCollection.insert(new BasicDBObject("token", tokenString)
				.append("userID", token.getUserID())
				.append("exp", token.getExp()).append("iat", token.getIat())
				.append("iss", token.getIss()).append("aud", token.getAud()));

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
		String tokenString = mJWESecurity.encoding(new Gson().toJson(token));
		BasicDBObject searchQry = new BasicDBObject("token", strToken);
		BasicDBObject changeDoc = new BasicDBObject();
		changeDoc.append("$set", new BasicDBObject("token", tokenString)
		.append("userID", token.getUserID())
		.append("exp", token.getExp()).append("iat", token.getIat())
		.append("iss", token.getIss()).append("aud", token.getAud()));
		
		//http://wannastop.tistory.com/486
		if(mCollection.update(searchQry, changeDoc).getN() > 0){
			return tokenString;
		}else{
			return TokenStatus.tokenUpdateFail.name();
		}
	}

	@Override
	public String verifyToken(String token, String aud) {
		// TODO Auto-generated method stub
		DBObject basic = mCollection.findOne(new BasicDBObject("token", token));	
		if(isUpdateToken(new Long(basic.get("exp").toString())).equals(TokenStatus.tokenExpiringsoon)){
			return updateToken(token, aud); //토큰 
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
	private TokenStatus isUserExist(String userID){
		
		if(mCollection.find(new BasicDBObject("userID", userID)).count()>0){
			mCollection.remove(new BasicDBObject("userID", userID));
			return TokenStatus.tokenExpired;
		}
		return TokenStatus.success;
		
	}

}
