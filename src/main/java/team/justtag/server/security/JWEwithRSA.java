package team.justtag.server.security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.NoSuchPaddingException;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.keys.BigEndianBigInteger;
import org.jose4j.lang.JoseException;

import team.justtag.server.main.Config;

public class JWEwithRSA {

	private RsaJsonWebKey mJsonWebKey;
		
	public void init() throws NoSuchAlgorithmException, JoseException{
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		clsKeyPairGenerator.initialize(Config.RSA_LENGTH);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		mJsonWebKey = new RsaJsonWebKey((RSAPublicKey) clsKeyPair.getPublic());
		mJsonWebKey.setPrivateKey(clsKeyPair.getPrivate());
	}
	
	@SuppressWarnings("unchecked")
	public String getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(mJsonWebKey.getPublicKey(), RSAPublicKeySpec.class);
		JSONObject json = new JSONObject();
		String e = BigEndianBigInteger.toBase64Url(clsPublicKeySpec.getPublicExponent());
		String n = BigEndianBigInteger.toBase64Url(clsPublicKeySpec.getModulus());
		json.put("n", n);
		json.put("e", e);
		return json.toJSONString();
	}
	public PrivateKey getPrivateKey() {
		// TODO Auto-generated method stub
		return mJsonWebKey.getPrivateKey();
	}
	public String decoding(String byteString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, JoseException{
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
		jwe.setKey(mJsonWebKey.getPrivateKey());
		jwe.setCompactSerialization(byteString);
		System.out.println(jwe.getPayload());
		return jwe.getPayload();
	}
	
	public String decoding(PrivateKey privateKey, String byteString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, JoseException{
		JsonWebEncryption jwe = new JsonWebEncryption();
		jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
		jwe.setKey(privateKey);
		jwe.setCompactSerialization(byteString);
		System.out.println(jwe.getPayload());
		return jwe.getPayload();
	}
	
}