package team.justtag.server.login.service;

import java.security.Key;
import java.util.Date;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

import team.justtag.server.login.vo.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class LoginService {
	private final DB db;
	private final DBCollection collection;

	public LoginService(DB db) {
		this.db = db;
		this.collection = db.getCollection("user");
	}

	public void createNewUser(String body) {
		User user = new Gson().fromJson(body, User.class);
		collection
				.insert(new BasicDBObject("user", user.getUser()).append(
						"password", user.getPassword()).append("createdOn",
						new Date()));
	}
	public String tokenServicex() {
		Key key = new AesKey(ByteUtil.randomBytes(16));
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload("Hello World!");
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(key);
		String serializedJwe;
		try {
			serializedJwe = jwe.getCompactSerialization();
			System.out.println("Serialized Encrypted JWE: " + serializedJwe);
			jwe = new JsonWebEncryption();
			jwe.setKey(key);
			jwe.setCompactSerialization(serializedJwe);
			return "Payload: " + jwe.toString();
		} catch (JoseException e) {
			// TODO Auto-generated catch block
			return "error" + e.getMessage().toString();
		}

	}
}
