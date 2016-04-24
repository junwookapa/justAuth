package com.junkapa.server.module.token;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.junkapa.server.framework.IController;
import com.junkapa.server.framework.IPersistence;
import com.junkapa.server.framework.IService;
import com.junkapa.server.module.token.controller.TokenController;
import com.junkapa.server.module.token.persistence.TokenIsuuerPersistence;
import com.junkapa.server.module.token.persistence.TokenLogPersistence;
import com.junkapa.server.module.token.persistence.TokenPersistence;
import com.junkapa.server.module.token.service.TokenService;

public class TokenModule extends AbstractModule{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(IController.class).to(TokenController.class);
		bind(IService.class).to(TokenService.class);

		Multibinder<IPersistence<?>> binder = Multibinder.newSetBinder(binder(), new TypeLiteral<IPersistence<?>>(){});
		binder.addBinding().to(TokenPersistence.class);
		binder.addBinding().to(TokenIsuuerPersistence.class);
		binder.addBinding().to(TokenLogPersistence.class);
	
	}

}
