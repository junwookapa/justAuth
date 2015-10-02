package team.justtag.server.test;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

public class TestDB {
	public void test(){
		BasicDBObject userObj = 
				new BasicDBObject()
				.append("user_id", "123")
				.append("user_name", "123")
				.append("user_group_id", "123")
				.append("password", "123")
				.append("role", "123")
				.append("email", "123")
				.append("store_id", "123");
		System.out.println(new Gson().toJson(userObj));
	//	System.out.println(new JWESecurity().encoding(new Gson().toJson(userObj)));
		// eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.5YQfDbvuA6I4Id9LSuof8Vkxw3oIxZ3iHPl-U6E19mK0e5DYTHYMyQ.KYTkny9w5GtoNDgpJFmQBA.goQmfsd17R00sgoEOitccu9G965LQqMmsdA0qA_9FhCFj1s6B4HuSQy7HjF-Qqzi3jErKPVCm0jLdAzzmRdVVYhAkY__NzH37WROT31HubmHA4D4mIWbVsYdY9PvWAXX8WjawIgOvcA0iWS70zWuS7VwrxIwOugQexud6WYx2oQ.DcWmFv8M_q2oPVsnJyO3kw
	}
	
	public void test2(){
		BasicDBObject basic1 = new BasicDBObject();
		BasicDBObject basic2 = new BasicDBObject();
		basic1.append("test1_field1", "test");
		basic1.append("test2_filed2", "test2");
		
		List<String> asd = new ArrayList<String>();
		asd.add("asd");
		asd.add("fgh");
	
		basic2.append("group_name", "2");
		basic2.append("description", "3");
		basic2.append("reg_date", new Date());
		basic2.append("users", asd);
				
		String json = new Gson().toJson(basic2);
		System.out.println(json);
		
	}
	
	public void test3(){
//System.out.println(new JWESecurity().keyGenerate("1234567890qwerty"));
				//System.out.println(new JWESecurity().keyGenerate(Config.KEY_STRING)+"");
			//	System.out.println(new JWEwithAES().decoding("eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.Xl8rAjoI9aqxqi5FQTT43RD7QzBRqT7IIkbTfGt6QZu5SKBxhu0KFg.wrhOycD2BaPW2KQ8qz-yHA.Wb2BS02dxgCvdq4efOQdmQ.4OkyRU3wD1f4OCz6qXQLmA"));
				//eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.ecI-M_GWuqf_8fjP1tl1dOheIxuHFRplEpX9bPCv11ij9J737vCssg.3DBSpwNkZC1SkHhwnCrwFw.Rv7sQpuMfqc5Ax_lAxfbgA.zX2vjpka1XAjA0eoyiP40w
				
				
	}
	 public void testtool() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(512);

		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

	
		 }

}
