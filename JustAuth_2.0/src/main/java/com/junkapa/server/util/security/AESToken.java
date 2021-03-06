package com.junkapa.server.util.security;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

public class AESToken {
	
	public static String encodingToken(String str, String keyString) {
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
	public static String encodingToken(JwtClaims claims, String keyString) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setPayload(claims.toJson());
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

	public static String decodingToken(String str, String keyString) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		Key key = new SecretKeySpec(keyString.getBytes(), "AES");
		jwe.setKey(key);
		try {
			jwe.setCompactSerialization(str);
			return jwe.getPayload();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}
}
