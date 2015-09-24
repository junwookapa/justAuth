package team.justtag.server.test;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.KeyGenerator;

import org.junit.Test;

import team.justtag.server.main.Config;
import team.justtag.server.user.model.UserGroup;
import team.justtag.server.util.JWESecurity;

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
		UserGroup usergroup = new UserGroup(basic2);
		System.out.println(usergroup.getUsers().get(0));
		System.out.println(usergroup.getUsers().get(1));
		
	}
	@Test
	public void test3(){
//System.out.println(new JWESecurity().keyGenerate("1234567890qwerty"));
				//System.out.println(new JWESecurity().keyGenerate(Config.KEY_STRING)+"");
				System.out.println(new JWESecurity().decoding("eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.dV7imqqkJHWKMJ0zvFCb6l7WY5anBmoYIEivmUu50mExn-EzMQcGIQ.MaGDHhBKEVZcxF4mL8LUVA.o0W9Djg_ZaoooOg4iD7HFw.bmZba_z_2FpdxfajnEP47w"));
				//eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.ecI-M_GWuqf_8fjP1tl1dOheIxuHFRplEpX9bPCv11ij9J737vCssg.3DBSpwNkZC1SkHhwnCrwFw.Rv7sQpuMfqc5Ax_lAxfbgA.zX2vjpka1XAjA0eoyiP40w
				
				
	}			

}
