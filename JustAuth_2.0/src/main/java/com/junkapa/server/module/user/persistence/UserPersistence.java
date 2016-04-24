package com.junkapa.server.module.user.persistence;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.db.DatabaseHandler;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.module.user.entity.User;

public class UserPersistence implements IPersistence<User>{

private final DatabaseHandler mDBHandler;
	
	@Inject
	public UserPersistence(DatabaseHandler handler){
		this.mDBHandler = handler;
	}
	
	@Override
	public Set<User> getList(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getOne(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
