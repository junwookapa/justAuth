package team.justtag.server.util;

import java.security.Key;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import team.justtag.server.main.Config;

public class JWESecurity {

	public Key initJsonWebKey(Key aes_key) {
		String base64EndcodedKey = aesToBase64(aes_key); // base 64key
		String jwk_shared_key = "{\"kty\":\"oct\",\"k\":\"" + base64EndcodedKey
				+ "\"}"; // JWT KEY
		try {
			return JsonWebKey.Factory.newJwk(jwk_shared_key).getKey();
		} catch (JoseException e) {
			return null;
		}
	}

	public String aesToBase64(Key keyParam) {
		Key key = keyParam;
		byte[] keyBytes = key.getEncoded();
		String base64EncodedKey = com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64
				.encodeBase64URLSafeString(keyBytes);
		return base64EncodedKey;
	}

	public String encoding(JSONObject json) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(json.toJSONString());
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
		jwe.setKey(Config.JWK);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}

	}

	public String encoding(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(str);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
		jwe.setKey(Config.JWK);

		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

	public String encoding(JwtClaims claims) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(claims.toJson());
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
		jwe.setKey(Config.JWK);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

	public String decoding(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		JsonWebKey jwk = null;
		jwe.setKey(Config.JWK);
		try {
			jwe.setCompactSerialization(str);
			return jwe.getPayload();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

}
