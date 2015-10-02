package team.justtag.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import team.justtag.server.main.Config;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.security.JWEManager;
import team.justtag.server.security.JWEwithRSA;
import team.justtag.server.user.service.UserService;
import team.justtag.server.util.JsonTransformer;

import com.mongodb.DB;

public class UserController {

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
					String funtionBlockJson = new String(request.bodyAsBytes(),	"UTF-8");
					System.out.println(funtionBlockJson);
					String decodingString = new JWEwithRSA().decoder(request.session().attribute("privateKey"), funtionBlockJson);
					System.out.println(decodingString);
					return mUserService.createUser(decodingString);
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
			
			System.out.println(request.body());
			String decodingString = new JWEwithRSA().decoder(request.session().attribute("privateKey"), request.body());
			UserStatus responseStatus = mUserService.login(decodingString);
			if (responseStatus.equals(UserStatus.success)) {
				response.status(201);
			} else {
				response.status(406);
			}
			return responseStatus;
		}, new JsonTransformer());
		
		post("/sign", "application/json",
				(request, response) -> {
					if(request.session().isNew() == true){
						JWEManager jweObj = new JWEManager();
						JWEwithRSA jWEwithRSA= new JWEwithRSA();
					//jWEwithRSA.init();
						request.session(true);
						request.session().attribute("privateKey", jweObj.getPrivateKey());
						response.cookie("publicKey", jweObj.getPublicKeyWithJson());
					}
				return response;
		},new JsonTransformer());
	
		post("/conn", "application/json",
				(request, response) -> {
					System.out.println(request.body());
					String decodingString = null;
					try{
					decodingString = new JWEwithRSA().decoder(request.session().attribute("privateKey"), request.body());
					}catch(Exception e){
						System.out.println("에롱"+e.getMessage());	
					}
					System.out.println(decodingString);
				return decodingString;

		},new JsonTransformer());
		get("/key", "application/json",
				(request, response) -> {
					JWEManager keyManager = new JWEManager();
					request.session().maxInactiveInterval(Config.SESSION_TIME);
					request.session().attribute("privateKey", keyManager.getPrivateKey());
					return keyManager.getPublicKeyWithJson();
		});	
	}
}
