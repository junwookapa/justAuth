package team.justtag.auth.token.vo;

import com.mongodb.BasicDBObject;

public class Token {
	private String userID; // 사용자 역할 admin or endUser..
	private String exp; // 만료시간
	private String iat; // 토큰 발급시간
	private String iss; // 토큰 발급자
	private String aud; // 토큰 사용처
	
	public Token(BasicDBObject dbObject) {
		this.userID = dbObject.getString("userID");
		this.exp = dbObject.getString("exp");
		this.iat = dbObject.getString("iat");
		this.iss = dbObject.getString("iss");
		this.aud = dbObject.getString("aud");
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	
}
