package com.junkapa.server.main;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.framework.IController;

public class ControllerManager {
	private final Set<IController> controllers;
	
	@Inject
	public ControllerManager(Set<IController> controllers) {
		this.controllers = controllers;
		
	}
	public void start(){
		for(IController controller : controllers)
			controller.setEndPoint();
	}
}
