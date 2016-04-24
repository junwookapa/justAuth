package com.junkapa.server.module.token.persistence;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.db.DatabaseHandler;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.module.token.entity.TokenIssuer;

public class TokenIsuuerPersistence implements IPersistence<TokenIssuer> {

	private final DatabaseHandler mDBHandler;

	@Inject
	public TokenIsuuerPersistence(DatabaseHandler handler) {
		this.mDBHandler = handler;
	}

	@Override
	public Set<TokenIssuer> getList(TokenIssuer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenIssuer getOne(TokenIssuer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(TokenIssuer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TokenIssuer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TokenIssuer entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
