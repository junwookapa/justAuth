package team.justtag.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

import java.security.KeyFactory;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.x9.KeySpecificInfo;
import org.jose4j.json.internal.json_simple.JSONObject;

import spark.Session;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.user.dao.UserGroupDaoImpl;
import team.justtag.server.user.model.UserGroup;
import team.justtag.server.user.service.UserService;
import team.justtag.server.util.JsonTransformer;
import team.justtag.server.util.RSASecurity;

import com.google.gson.Gson;
import com.mongodb.DB;

public class UserController {

	private static RSASecurity asd;
	private UserService mUserService;
	private DB mDB;

	public UserController(UserService userService, DB db) {
		this.mDB = db;
		this.mUserService = userService;
		setupEndpoints();
	}

	@SuppressWarnings("unchecked")
	private void setupEndpoints() {

		// createUser
		post("/user", "application/json",
				(request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(),
							"UTF-8");
					return mUserService.createUser(funtionBlockJson);
				}, new JsonTransformer());
		// getUser Info
		get("/user/:id", "application/json",
				(request, response) -> mUserService.findUserbyUserID(request
						.params(":id")), new JsonTransformer());
		// getAll User Info
		get("/users", "application/json",
				(request, response) -> mUserService.findAllUsers(),
				new JsonTransformer());
		// delete User
		delete("/user",
				"application/json",
				(request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(),
							"UTF-8");
					
					return mUserService.deleteUser(funtionBlockJson).toString();
				}, new JsonTransformer());

		// login
		post("/login", "application/json", (request, response) -> {
			UserStatus responseStatus = mUserService.login(request.body());
			if (responseStatus.equals(UserStatus.success)) {
				response.status(201);
			} else {
				response.status(406);
			}
			return responseStatus;
		}, new JsonTransformer());

		get("/test2", "application/json",
				(request, response) -> {
					JSONObject	userGroup1	=new JSONObject();
					userGroup1.put("group_name", "123");
					userGroup1.put("description", "456");

				UserGroup userGroup = new Gson().fromJson(userGroup1.toJSONString() , UserGroup.class);
				userGroup.setReg_date(new Date().toGMTString());
				List<String> arr = new ArrayList<String>();
				arr.add("123");
				arr.add("456");
				userGroup.setUsers(arr);

				UserGroupDaoImpl asd = new UserGroupDaoImpl(mDB);

				asd.createUserGroup(userGroup);
				//asd.addUser("456", "asd");
				//asd.deleteUser("456", "123");
				return null;
					
		},new JsonTransformer());
		
		post("/sign", "application/json",
				(request, response) -> {
					if(request.session().attribute("publicKey") == null){
					asd= new RSASecurity();
					request.session(true);
					request.session().attribute("publicKey", asd.getPublicKey());
					response.cookie("publicKey", request.session().attribute("publicKey").toString());
					KeyFactory fact = KeyFactory.getInstance("RSA");
					
					}
					System.out.println(request.session().attribute("publicKey").toString());
					response.body("asd");
				return response;

		},new JsonTransformer());
	
		post("/conn", "application/json",
				(request, response) -> {
				//	System.out.println(request.body());
				//	System.out.println("param : "+request.params(":para"));
					System.out.println(request.body());
					String kk = null;
					try{
					kk = asd.decoding(request.body());
					}catch(Exception e){
						System.out.println("에롱"+e.getMessage());
						
					}
					System.out.println(kk);
				return kk;

		},new JsonTransformer());
	}


}
