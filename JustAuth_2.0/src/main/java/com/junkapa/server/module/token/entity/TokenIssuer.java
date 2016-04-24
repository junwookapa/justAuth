package com.junkapa.server.module.token.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.junkapa.server.module.user.entity.User;

@Entity
@Table(name="token_issuer")
public class TokenIssuer {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private String _id;
	
	private String owner; // user
	private String owner_group; //ownner group
	@OneToMany
	private Set<Token> token; // 토큰목록
	@OneToMany
	private Set<User> user;
	
	/*
	 * Constructor
	 */
	
	public TokenIssuer(){}
	
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner_group() {
		return owner_group;
	}
	public void setOwner_group(String owner_group) {
		this.owner_group = owner_group;
	}
	public Set<Token> getToken() {
		return token;
	}
	public void setToken(Set<Token> token) {
		this.token = token;
	}
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}
	
	

}
