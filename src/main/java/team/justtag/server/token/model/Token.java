package team.justtag.server.token.model;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Token {
	private String _id;
	private String token; // 토큰
	private String user_id; // 사용자 아이디
	private String exp; // 만료시간
	private String iat; // 토큰 발급시간
	private String iss; // 토큰 발급자
	private String aud; // 토큰 사용처
	private String aes_key;

	public Token(BasicDBObject dbObject) {
		this._id = ((ObjectId) dbObject.get("_id")).toString();
		this.token = dbObject.getString("token");
		this.user_id = dbObject.getString("user_id");
		this.exp = dbObject.getString("exp");
		this.iat = dbObject.getString("iat");
		this.iss = dbObject.getString("iss");
		this.aud = dbObject.getString("aud");
		this.aud = dbObject.getString("aes_key");
	}
	public Token() {
	}

	public String getAes_key() {
		return aes_key;
	}

	public void setAes_key(String aes_key) {
		this.aes_key = aes_key;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
