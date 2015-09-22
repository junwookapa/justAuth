package team.justtag.server.util;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public abstract class ServiceGeneric<T> {
	final DB db;
	final DBCollection collection;

	public ServiceGeneric(DB db, String collectionName) {
		this.db = db;
		this.collection = db.getCollection(collectionName);

	}

	public abstract boolean create(T t);

	public abstract boolean update(T t);

	public abstract List<T> findAll();

	public boolean delete(String type, String value) {
		try {
			collection.insert(new BasicDBObject(type, value));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public T findOneByOne(String type, String value) {
		try {
			return (T) collection.findOne(new BasicDBObject(type, value));
		} catch (Exception e) {
			return null;
		}
	}

}
