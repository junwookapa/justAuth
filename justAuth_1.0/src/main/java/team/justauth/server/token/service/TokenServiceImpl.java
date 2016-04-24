package team.justauth.server.token.service;

import org.jose4j.json.internal.json_simple.JSONObject;

import team.justauth.server.main.Config;
import team.justauth.server.main.Status.TokenStatus;
import team.justauth.server.security.AESToken;
import team.justauth.server.token.dao.TokenDao;
import team.justauth.server.token.dao.TokenDaoImpl;
import team.justauth.server.token.model.Token;
import team.justauth.server.util.Log;
import team.justauth.server.util.RandomString;

import com.google.gson.Gson;
import com.mongodb.DB;

public class TokenServiceImpl implements TokenService {

	private final TokenDao mTokenDao;

	public TokenServiceImpl(DB db) {
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
		String tokenString = AESToken.encodingToken(new Gson().toJson(token), token.getAes_key());
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
		Log.writeLog("[토큰발급]"+tokenString);
		return tokenString;
	}
	
	public String tokenUpdate(String token, String aud){
		
		Token tokenObj = mTokenDao.getTokenByToken(token);
		System.out.println(tokenObj.get_id());
		System.out.println(tokenObj.getUser_id());
		tokenObj.setAes_key(new RandomString(16).nextString());
		tokenObj.setToken(null);
		String tokenString = AESToken.encodingToken(new Gson().toJson(tokenObj), tokenObj.getAes_key());
		tokenObj.setToken(tokenString);
		mTokenDao.updateToken(tokenObj.get_id(), tokenObj);
		String upToken = mTokenDao.getTokenByUserID(tokenObj.getUser_id()).getToken();
		Log.writeLog("[토큰재발급]"+tokenString);
		return upToken;
		//System.out.println("토큰변경"+mTokenDao.getTokenByUserID(tokenObj.get_id()).getToken());
	}

	@Override
	public TokenStatus verifyToken(String token, String aud) {
		Token tokenObj = null;
		long expireTime;
		try{
			tokenObj = mTokenDao.getTokenByToken(token);
			expireTime = new Long(tokenObj.getExp());
						
			Token inputToken = new Gson().fromJson(AESToken.decodingToken(token, tokenObj.getAes_key()), Token.class);
			
			// 인증방식은 입력토큰과 db에 저장된 토큰을 비교 한 후
			// 2차 인증으로 유저아이디를 서로 비교 한다.
			// 인증 방식은 추후 변경 될 수 있다.
			if(!tokenObj.getUser_id().equals(inputToken.getUser_id())){
				return TokenStatus.notFoundToken;
			}

		}catch(NullPointerException e)   {
			return TokenStatus.notFoundToken;
		}
		switch(isExpiredToken(expireTime)){
			case tokenExpired:
				return TokenStatus.tokenExpired;
			case success:
				Log.writeLog("[토큰인증]"+token);
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
		try{
		if(mTokenDao.getTokenIDByToken(token) != null){
			String token_id = mTokenDao.getTokenIDByToken(token);
			mTokenDao.deleteToken(token_id);
			Log.writeLog("[토큰삭제]"+token);
			return TokenStatus.success;
		}else{
			return TokenStatus.notFoundToken;
		}}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public TokenStatus isUpdateToken(long expireTime) {
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

	@Override
	public String getUserID(String token) {
		return mTokenDao.getTokenByToken(token).getUser_id();
	}


}