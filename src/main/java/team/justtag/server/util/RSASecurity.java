package team.justtag.server.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;
import org.junit.Test;

public class RSASecurity {

	private Key mPublicKey;
	private Key mPrivateKey;
	
	
	public RSASecurity(){
		try {
			init();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JoseException e) {
			// TODO: handle exception
		}
	}
	
	public void init() throws NoSuchAlgorithmException, JoseException{
		RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
			mPublicKey = rsaJsonWebKey.getPublicKey();
			mPrivateKey = rsaJsonWebKey.getPrivateKey();
	}
	
	@SuppressWarnings("unchecked")
	public String getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
		KeyFactory fact = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(mPublicKey, RSAPublicKeySpec.class);
		JSONObject json = new JSONObject();
		json.put("n", clsPublicKeySpec.getModulus());
		json.put("e", clsPublicKeySpec.getPublicExponent());
		return json.toJSONString();
	}
	
	public Key getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
		return mPrivateKey;
	}
	
	public String decoding(String byteString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		
		Cipher clsCipher = Cipher.getInstance("RSA");
		
		
		System.out.println("pppp" + byteString);
		JsonWebSignature jws = new JsonWebSignature();
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
	    jws.setKey(mPrivateKey);
		//String strCipher = new String(org.bouncycastle.util.encoders.Hex.encode(arrCipherData));
	    clsCipher.init(Cipher.DECRYPT_MODE, jws.getKey());
		
		
		byte[] arrData = null;
		try {
			arrData = clsCipher.doFinal(byteString.getBytes());
		} catch (IllegalBlockSizeException e) {
			System.out.println("ille" +e.getMessage());
			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.out.println("badpadding" +e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("asdasd");
		String strResult = new String(arrData);
		System.out.println("result(" + strResult + ")");
		return null;
		
	}
	
	public String decoding2(String str) {
		JsonWebEncryption jwe = new JsonWebEncryption();
		/*jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA_OAEP);
		jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);*/
			
		jwe.setKey(mPrivateKey);
		try {
			jwe.setCompactSerialization(str);
			System.out.println(jwe.getPayload());
			return jwe.getPayload();
		} catch (JoseException e) {
			return e.getMessage();
		}
	}
	
	
	@Test
	public void initJsonWebKey() throws Exception {
		// RSA 공개키/개인키를 생성한다.
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

	     byte[] input = "abcdefg hijklmn".getBytes();
	     Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	     SecureRandom random = new SecureRandom();
	     KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

	     generator.initialize(2048, random); // 여기에서는 128 bit 키를 생성하였음
	     KeyPair pair = generator.generateKeyPair();
	     Key pubKey = pair.getPublic();  // Kb(pub) 공개키
	     Key privKey = pair.getPrivate();// Kb(pri) 개인키

	     // 공개키를 전달하여 암호화
	     cipher.init(Cipher.ENCRYPT_MODE, pubKey);
	     byte[] cipherText = cipher.doFinal(input);
	     System.out.println("cipher: ("+ cipherText.length +")"+ new String(cipherText));
	     
	     // 개인키를 가지고있는쪽에서 복호화
	     cipher.init(Cipher.DECRYPT_MODE, privKey);
	     byte[] plainText = cipher.doFinal(cipherText);
	     System.out.println("plain : " + new String(plainText));
	}

}