package com.junkapa.server.module.token.persistence;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.db.DatabaseHandler;
import com.junkapa.server.framework.IPersistence;

import antlr.Token;

public class TokenPersistence implements IPersistence<Token>{
	
	private final DatabaseHandler mDBHandler;
	
	@Inject
	public TokenPersistence(DatabaseHandler handler){
		this.mDBHandler = handler;
	}

	@Override
	public Set<Token> getList(Token entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token getOne(Token entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Token entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Token entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Token entity) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
