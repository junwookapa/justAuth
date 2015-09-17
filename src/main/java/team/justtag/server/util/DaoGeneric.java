package team.justtag.server.util;

import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DaoGeneric<T> {
	final DB db;
	final DBCollection collection;
	final T vo;
	//mongoDB는 DAO가 궂이 필요하지 않은 것 같아 하다 말음
	
	public DaoGeneric(DB db, String collectionName, T vo) {
		this.db = db;
		this.collection = db.getCollection(collectionName);
		this.vo = vo;
	}
	public boolean create(Map<?, ?> map){
		try{
			collection.insert(new BasicDBObject(map));
			return true;
		}catch(Exception e){
			return false;
		}	
	}
	public boolean update(){
		return false;
	}
	public boolean delete(){
		return false;	
	}
	public List<T> findAll(){
		return null;
	}
	public T findOneByOne(String type, String value){
		return null;
	}
	

}
