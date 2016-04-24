package com.junkapa.server.main;

import java.util.Set;

import javax.inject.Inject;

import com.junkapa.server.framework.IController;
import com.junkapa.server.framework.IPersistence;

public class PersistenceManager {
private final Set<IPersistence<Object>> persistences;
	
	@Inject
	public PersistenceManager(Set<IPersistence<Object>>persistences) {
		this.persistences = persistences;
		
	}
	public void callMessage(Object object){
		for(IPersistence<Object> persistence : persistences)
			persistence.getOne(object);
	}
}
