package team.justtag.server.security;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import team.justtag.server.main.Config;

public class JWEwithAES {

	private Key mKEy;
	public Key initJsonWebKey(Key aes_key) {
		String base64EndcodedKey = aesToBase64(aes_key); // base 64key
		String jwk_shared_key = "{"
				+ "\"kty\":\"oct\",\"k\":\"" + base64EndcodedKey
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
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		jwe.setKey(Config.AES_KEY);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}

	}

	public String encodingToken(String str, String keyString) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(str);
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		Key key = new SecretKeySpec(keyString.getBytes(), "AES");
		jwe.setKey(key);

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
		jwe.setKey(Config.AES_KEY);
		try {
			return jwe.getCompactSerialization();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

	public String decoding(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		JsonWebKey jwk = null;
		jwe.setKey(Config.AES_KEY);
		try {
			jwe.setCompactSerialization(str);
			return jwe.getPayload();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}

}
