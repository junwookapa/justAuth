package com.junkapa.server.module.user;

import com.google.inject.AbstractModule;
import com.junkapa.server.framework.IController;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.framework.IService;
import com.junkapa.server.module.user.controller.UserController;
import com.junkapa.server.module.user.persistence.UserPersistence;
import com.junkapa.server.module.user.service.UserService;

public class UserModule extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(IController.class).to(UserController.class);
		bind(IService.class).to(UserService.class);
		bind(IPersistence.class).to(UserPersistence.class);
	}

}
