package com.junkapa.server.db;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
///http://spykol.blogspot.kr/2012/04/efficient-multi-threading-application.html
public class DatabaseHandler implements Runnable{
	private static BlockingQueue<Object> databaseQueue = new LinkedBlockingQueue<Object>();  
    private boolean running;  
    public DatabaseHandler(){  
         running = true;  
    }  
    @Override  
    public void run() {  
         while(running){  
              try {  
                   Object anObject = databaseQueue.take();  
             /*      if(anObject instanceof test){  
                	   DataBaseService.insertEntity((test)anObject);  
                   }  */
              } catch (InterruptedException e) {  
                  
                   continue;  
              }  
         }  
    }  
    public static BlockingQueue<Object> getDatabaseQueue() {  
         return databaseQueue;  
    }  
    public static void setDatabaseQueue(BlockingQueue<Object> databaseQueue) {  
         DatabaseHandler.databaseQueue = databaseQueue;  
    }       

}
