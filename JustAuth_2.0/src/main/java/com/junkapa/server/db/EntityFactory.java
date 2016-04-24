package com.junkapa.server.db;

public class EntityFactory {
	private EntityFactory(){
    }
    private static class SingletonHolder{
        static final EntityFactory single = new EntityFactory();
    }
    public static EntityFactory getInstatnce(){
        return SingletonHolder.single;
    }
}
