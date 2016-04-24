package com.junkapa.server.module.user.service;

import javax.inject.Inject;

import com.junkapa.server.db.DatabaseHandler;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.framework.IService;

public class UserService implements IService{
	private final IPersistence mPersistence;
	private final DatabaseHandler mDBHandler;
	
	@Inject
	public UserService(IPersistence persistence, DatabaseHandler handler){
		this.mPersistence = persistence;
		this.mDBHandler = handler;
		System.out.println("유저 인젝션 성공");
	}

	@Override
	public void getModule() {
		// TODO Auto-generated method stub
		System.out.println("유저 톡흔");
	}
}
