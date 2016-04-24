package com.junkapa.server.module.token.controller;

import static spark.Spark.get;

import javax.inject.Inject;

import static spark.Spark.before;

import com.junkapa.server.framework.IController;
import com.junkapa.server.framework.IService;

public class TokenController implements IController{

	private final IService mService;
	
	@Inject
	public TokenController(IService service){
		this.mService = service;
		setEndPoint();
	}
	
	@Override
	public void setEndPoint() {
		
		
		before((request, response) ->{
				System.out.println("token 비포테스트");
				mService.getModule();
			});
		
		
		get("/token", "application/json", (request, response) ->{
			return "토큰 컨트롤러";
			});
		
	}


}
