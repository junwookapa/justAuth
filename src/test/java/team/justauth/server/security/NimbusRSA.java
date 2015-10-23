package team.justauth.server.security;


import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import org.eclipse.jetty.util.log.Log;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.keys.BigEndianBigInteger;
import org.junit.Test;

public class NimbusRSA {
	
	public void encodingTest() throws NoSuchAlgorithmException, InvalidKeySpecException{
		String e = "AQAB";
		String n = "p0CACG8UfwQP7P2uwfevaNViDqyjRRus2oYRJqCCPSSdjPnO82ac4NcCUPmBEeLxrRFwTK-YE1wiPmzC_H3Skvzg3FibX2IizCOjyAUA74iszkQ8xbrhBd43sNbpsZdcAyVshxB2JXb286C0ppVsQZ8xH-1oDK3V0XMrizIE_oVsclEfVcGCcinslQU1YGQ_2zrQt8YdstdinWQqgHltKsNjLAW3v9ooJ1QBmujbuzn25RsqJf0DZP_7GqE9fai-jkapf4fj5yRjwlL3600Q1UzoADUWUNOe2aFhhmVBSO2MG5_yrGJtIPcM9DQUckuXoMGAe-uG06HIcZw0xFwfPw";
		
		//BigEndianBigInteger.toBase64Url(clsPublicKeySpec.getPublicExponent());
		RSAPublicKeySpec publickKey = new RSAPublicKeySpec(BigEndianBigInteger.fromBase64Url(n), BigEndianBigInteger.fromBase64Url(e));
		JSONObject jsonObj = new JSONObject();
		
		KeyFactory fact = KeyFactory.getInstance("RSA");
	//	RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
		fact.generatePublic(publickKey);
		
		jsonObj.put("user_id", "admin");
		jsonObj.put("user_password", "admin");
		System.out.println(new JWEUtil().encoder(fact.generatePublic(publickKey), jsonObj.toJSONString()));
		
	}
	@Test
	public void osTest(){
		System.out.println("운영체제 종류: " + System.getProperty("os.name"));
	}
}
