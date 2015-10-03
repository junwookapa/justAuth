package team.justtag.server.token.dao;

import org.bson.types.ObjectId;

import team.justtag.server.main.Status.DBStatus;
import team.justtag.server.token.model.Token;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public class TokenDaoImpl implements TokenDao{
	
	private final DBCollection mCollection;
	public TokenDaoImpl(DB db){
		this.mCollection = db.getCollection("token");
	}
	@Override
	public DBStatus createToken(Token token) {
		try{
			mCollection.insert(new BasicDBObject()
					.append("token", token.getToken())
					.append("user_id", token.getUser_id())
					.append("exp", token.getExp())
					.append("iat", token.getIat())
					.append("iss", token.getIss())
					.append("aud", token.getAud())
					.append("aes_key", token.getAes_key()));
			return DBStatus.success;
		}catch(MongoException e){
			System.out.println(e.getMessage());
			return DBStatus.insertFail;
		}
	}
	@Override
	public DBStatus updateToken(String _id, Token token) {
		try{
			mCollection.update(
					new BasicDBObject("_id", new ObjectId(_id))
					, new BasicDBObject("$set", new BasicDBObject()
					.append("token", token.getToken())
					.append("user_id", token.getUser_id())
					.append("exp", token.getExp())
					.append("iat", token.getIat())
					.append("iss", token.getIss())
					.append("aud", token.getAud())
					.append("aes_key", token.getAes_key())));
			return DBStatus.success;
		}catch(MongoException e){
			return DBStatus.updateFail;
		}
	}
	@Override
	public DBStatus deleteToken(String _id) {
		try{
			mCollection.remove(new BasicDBObject("_id", new ObjectId(_id)));
			return DBStatus.success;
		}catch(Exception e){
			return DBStatus.deleteFail;
		}
	}
	@Override
	public Token getTokenByToken(String token) {
		try{
			 return new Token((BasicDBObject) mCollection.findOne(new BasicDBObject("token", token)));
		}catch(MongoException e){
			return null;
		}
	}
	@Override
	public Token getTokenByUserID(String user_id) {
		try{
			 return new Token((BasicDBObject) mCollection.findOne(new BasicDBObject("user_id", user_id)));
		}catch(MongoException e){
			return null;
		}
	}
	@Override
	public String getTokenIDByUserID(String user_id) {
		try{
			 return new Token((BasicDBObject) mCollection.findOne(new BasicDBObject("user_id", user_id))).get_id();
		}catch(MongoException e){
			return null;
		}
	}
	@Override
	public String getTokenIDByToken(String token) {
		try{
			 return new Token((BasicDBObject) mCollection.findOne(new BasicDBObject("token", token))).get_id();
		}catch(MongoException e){
			return null;
		}
	}
	@Override
	public DBStatus isUserExist(String user_id) {
		if(mCollection.findOne(new BasicDBObject("user_id", user_id)) != null){
			return DBStatus.duplicated;
		}else {
			return DBStatus.noproblem;
		}
		
	}

	
	

}
