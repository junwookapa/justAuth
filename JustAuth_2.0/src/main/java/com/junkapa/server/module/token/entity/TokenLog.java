package com.junkapa.server.module.token.entity;

import java.util.Set;

import javax.persistence.OneToMany;

public class TokenLog {
	@OneToMany
	private Set<Token> token;
	
	/*
	 * Constructor
	 */
	
	public TokenLog(){}

	
	/* gen
	 * setter
	 * getter
	 * */
	public Set<Token> getToken() {
		return token;
	}

	public void setToken(Set<Token> token) {
		this.token = token;
	}
	
	

}
