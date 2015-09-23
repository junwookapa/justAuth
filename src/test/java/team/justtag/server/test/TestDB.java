package team.justtag.server.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;

import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.Test;

import team.justtag.server.main.Config;
import team.justtag.server.user.model.User;
import team.justtag.server.user.model.UserGroup;
import team.justtag.server.util.JWESecurity;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Hex;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

public class TestDB {
	@Test
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
		UserGroup usergroup = new UserGroup(basic2);
		System.out.println(usergroup.getUsers().get(0));
		System.out.println(usergroup.getUsers().get(1));
		
	}
	@Test
	public void test3(){
		String asd = "hello world";
		System.out.println(Hex.encodeHexString(Config.AES_TOKEN.getBytes()));
		System.out.println(Hex.encodeHex(Config.AES_TOKEN.getBytes()));
		System.out.println(Hex.encodeHex(Config.AES_TOKEN.getBytes()));
	//	System.out.println(Config.SECRET_KEY.getEncoded());
	//	System.out.println(Hex.encodeHexString(Config.SECRET_KEY));
		//System.out.println(new String(Config.SECRET_KEY.toString()));
	}

}
