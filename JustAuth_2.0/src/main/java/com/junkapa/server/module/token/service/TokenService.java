package com.junkapa.server.module.token.service;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.framework.IService;

public class TokenService implements IService{
	
	private final Set<IPersistence<?>> mPersistence;
	
	@Inject
	public TokenService(Set<IPersistence<?>> persistence){
		this.mPersistence = persistence;
		System.out.println("토큰 인젝션 성공");
	}

	@Override
	public void getModule() {
		// TODO Auto-generated method stub
		System.out.println("톡흔");
		System.out.println(mPersistence.size()+"");
		
	}

}
