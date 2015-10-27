package team.justauth.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.before;

import org.junit.Before;

import team.justauth.server.main.Config;
import team.justauth.server.main.Status.UserStatus;
import team.justauth.server.security.JWEManager;
import team.justauth.server.security.JWEUtil;
import team.justauth.server.user.service.UserService;
import team.justauth.server.util.JsonTransformer;
import team.justauth.server.util.Log;

public class UserController {

	private UserService mUserService;

	public UserController(UserService userService) {
		this.mUserService = userService;
		setupEndpoints();
	}

	@SuppressWarnings("unchecked")
	private void setupEndpoints() {
		
		// getUser Info
		get("/user/:id", "application/json", (request, response) -> 
					mUserService.findUserbyUserID(request.params(":id")), new JsonTransformer());
		// delete User
		delete("/user", "application/json", (request, response) -> {
							String funtionBlockJson = new String(request.bodyAsBytes(), "UTF-8");
							return mUserService.deleteUser(funtionBlockJson).toString();
						}, new JsonTransformer());
		
		
		// getAll User Info
		get("/users", "application/json", (request, response) -> mUserService.findAllUsers(request.headers("token"), response.raw().getHeader("user_id")), new JsonTransformer());
				
		// sign
		post("/sign", "application/json", (request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(),	"UTF-8");
					String decodingString = JWEUtil.decoder(request.session().attribute("privateKey"), funtionBlockJson);
					return mUserService.createUser(decodingString);
				}, new JsonTransformer());			
		
		// login
		post("/login", "application/json", (request, response) -> {
			String decodingString = JWEUtil.decoder(request.session().attribute("privateKey"), request.body());
			UserStatus responseStatus = mUserService.login(decodingString);
			if (responseStatus.equals(UserStatus.success)) {
				response.status(201);
			} else {
				response.status(204);
			}
			return responseStatus;
		}, new JsonTransformer());
		// key
		get("/key", "application/json",
				(request, response) -> {
					JWEManager keyManager = new JWEManager();
					request.session().maxInactiveInterval(Config.SESSION_TIME);
					request.session().attribute("privateKey", keyManager.getPrivateKey());
					response.header("publickey", keyManager.getPublicKeyWithJson());
					Log.writeLog("[RSA_PublicKey]"+keyManager.getPublicKeyWithJson());
					return keyManager.getPublicKeyWithJson();
		});
		
/*		before("/testp", "application/json",
				(request, response) -> {
				//	request.attribute("test", "testvalue");
					response.body("test");
					
		});
		get("/testp", "application/json",
				(request, response) -> {
					String test = response.body()+": ok";
				//	response.body("zxc");
					return test;
					
		});*/
	}
}
