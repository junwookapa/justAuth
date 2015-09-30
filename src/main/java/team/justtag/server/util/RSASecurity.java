package team.justtag.server.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKey.OutputControlLevel;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

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
		KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		clsKeyPairGenerator.initialize(512);
		KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		mPublicKey = clsKeyPair.getPublic();
		mPrivateKey = clsKeyPair.getPrivate();
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
	
	public String decoding(String byteString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, JoseException{
		
		JsonWebKey webKey = JsonWebKey.Factory.newJwk(mPrivateKey);
	//	json
		Cipher clsCipher = Cipher.getInstance("RSA");
		
		System.out.println("pppp" + byteString);
		 String jwkJson = "{\"kty\":\"oct\",\"k\":\"Fdh9u8rINxfivbrianbbVT1u232VQBZYKx1HGAGPt2I\"}";
		    JsonWebKey jwk = JsonWebKey.Factory.newJwk(jwkJson);
		
		
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
	
	public String decoding2(String byteString) throws JOSEException  {
		RSADecrypter decrypter = new RSADecrypter((RSAPrivateKey) mPrivateKey);
		JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP, EncryptionMethod.A128GCM);
		JWTClaimsSet jwtClaims2 = new JWTClaimsSet.Builder().issuer("asd").build();
		EncryptedJWT jwtdes = new EncryptedJWT(header, jwtClaims2);
		// Decrypt
		jwtdes.decrypt(decrypter);
		return jwtdes.getPayload().toString();
		
		
	}
	
	
	public String decoding3(String str) {
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
	

}