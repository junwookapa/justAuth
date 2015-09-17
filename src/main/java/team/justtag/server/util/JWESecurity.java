package team.justtag.server.util;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import team.justtag.server.main.Config;

public class JWESecurity {


	public String encoding(JSONObject json) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(json.toJSONString());
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(Config.SECRET_KEY);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}
	
	public String encoding(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(str);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(Config.SECRET_KEY);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

	public String encoding(JwtClaims claims) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(claims.toJson());
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(Config.SECRET_KEY);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

	public String decoding(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setKey(Config.SECRET_KEY);
		try {
			jwe.setCompactSerialization(str);
			return jwe.getPayload();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

}
