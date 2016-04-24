package com.junkapa.server.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
	private static EntityManagerFactory emf;

	public EntityManagerHelper() {
	}

	public void closeEmf() {
		if (emf.isOpen() || emf != null) {
			emf.close();
		}
		emf = null;
	}

	public static EntityManager getEntity() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("teesss");
		}
		return emf.createEntityManager();
	}
}
