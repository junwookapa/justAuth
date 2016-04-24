package com.junkapa.server.module.user.controller;

import static spark.Spark.before;
import static spark.Spark.get;

import javax.inject.Inject;

import com.junkapa.server.framework.IController;
import com.junkapa.server.framework.IService;

public class UserController implements IController{

	private final IService mService;
	
	@Inject
	public UserController(IService service){
		this.mService = service;
		setEndPoint();
	}
	
	@Override
	public void setEndPoint() {
		get("/user", "application/json", (request, response) ->{
			return "유저컨트롤러";
			});
		before((request, response) ->{
			System.out.println("token 비포테스트2");
			mService.getModule();
		});
		
	}

}
