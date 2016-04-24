package com.junkapa.server.module.token.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.junkapa.server.module.user.entity.User;

@Entity
@Table(name="token")
public class Token {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private String _id;
	private String token; // 토큰
	@ManyToOne
	private User user; // 사용자 아이디
	private String exp; // 만료시간
	private String iat; // 토큰 발급시간
	@ManyToOne
	private TokenIssuer iss; // 토큰 발급자
	private String aud; // 토큰 사용처
	private String aes_key; // aes_key
	
	/*
	 * Constructor
	 */
	
	public Token(){}
	
	/* gen
	 * setter
	 * getter
	 * */
	
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
	public User getUser_id() {
		return user;
	}
	public void setUser_id(User user_id) {
		this.user = user_id;
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
	public TokenIssuer getIss() {
		return iss;
	}
	public void setIss(TokenIssuer iss) {
		this.iss = iss;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getAes_key() {
		return aes_key;
	}
	public void setAes_key(String aes_key) {
		this.aes_key = aes_key;
	}

}
