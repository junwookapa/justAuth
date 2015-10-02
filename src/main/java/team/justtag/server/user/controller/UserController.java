package team.justtag.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import team.justtag.server.main.Config;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.security.JWEManager;
import team.justtag.server.security.JWEUtil;
import team.justtag.server.user.model.User;
import team.justtag.server.user.service.UserService;
import team.justtag.server.util.JsonTransformer;

import com.google.gson.Gson;
import com.mongodb.DB;

public class UserController {

	private UserService mUserService;

	public UserController(UserService userService) {
		this.mUserService = userService;
		setupEndpoints();
	}

	@SuppressWarnings("unchecked")
	private void setupEndpoints() {
		// createUser
		post("/user", "application/json", (request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(),	"UTF-8");
					String decodingString = new JWEUtil().decoder(request.session().attribute("privateKey"), funtionBlockJson);
					return mUserService.createUser(decodingString);
				}, new JsonTransformer());
		
		// getUser Info
		get("/user/:id", "application/json", (request, response) -> 
		mUserService.findUserbyUserID(request.params(":id")), new JsonTransformer());
		// getAll User Info
		get("/users", "application/json", (request, response) -> mUserService.findAllUsers(), new JsonTransformer());
		// delete User
		delete("/user", "application/json", (request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(), "UTF-8");
					return mUserService.deleteUser(funtionBlockJson).toString();
				}, new JsonTransformer());
		// login
		post("/login", "application/json", (request, response) -> {
			String decodingString = new JWEUtil().decoder(request.session().attribute("privateKey"), request.body());
			UserStatus responseStatus = mUserService.login(decodingString);
			if (responseStatus.equals(UserStatus.success)) {
				response.status(201);
			} else {
				response.status(204);
			}
			return responseStatus;
		}, new JsonTransformer());
		
		get("/key", "application/json",
				(request, response) -> {
					JWEManager keyManager = new JWEManager();
					request.session().maxInactiveInterval(Config.SESSION_TIME);
					request.session().attribute("privateKey", keyManager.getPrivateKey());
					return keyManager.getPublicKeyWithJson();
		});
	}
}
