package com.junkapa.server.module.token.persistence;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.db.DatabaseHandler;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.module.token.entity.TokenLog;

public class TokenLogPersistence implements IPersistence<TokenLog> {

	private final DatabaseHandler mDBHandler;

	@Inject
	public TokenLogPersistence(DatabaseHandler handler) {
		this.mDBHandler = handler;
	}

	@Override
	public Set<TokenLog> getList(TokenLog entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenLog getOne(TokenLog entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(TokenLog entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TokenLog entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TokenLog entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
