package com.junkapa.server.util.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import org.jose4j.jwk.RsaJsonWebKey;

import com.junkapa.server.common.Common;

public class JWEManager {

	private RsaJsonWebKey mJsonWebKey;
	public JWEManager() throws NoSuchAlgorithmException{
		generateKey();
	}

	public void generateKey() throws NoSuchAlgorithmException {
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator
				.getInstance("RSA");
		clsKeyPairGenerator.initialize(Common.RSA_LENGTH);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		mJsonWebKey = new RsaJsonWebKey((RSAPublicKey) clsKeyPair.getPublic());
		mJsonWebKey.setPrivateKey(clsKeyPair.getPrivate());
	}

	public PublicKey getPublicKey() {
		return mJsonWebKey.getPublicKey();
	}

	public PrivateKey getPrivateKey() {
		return mJsonWebKey.getPrivateKey();
	}

	public String getPublicKeyWithJson() throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		return new JWEUtil().publicKeyConvertJsonString(mJsonWebKey
				.getPublicKey());
	}
	

}
