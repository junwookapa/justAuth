package team.justtag.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import team.justtag.server.main.Status.UserStatus;
import team.justtag.server.user.service.UserGroupService;
import team.justtag.server.user.service.UserService;
import team.justtag.server.util.JsonTransformer;

public class UserController {

	private UserService mUserService;

	public UserController(UserService userService) {

		this.mUserService = userService;
		setupEndpoints();
	}

	private void setupEndpoints() {
		
		// user
		post("/user", "application/json",
				(request, response) -> {
					String funtionBlockJson = new String(request.bodyAsBytes(),
							"UTF-8");
					return mUserService.createUser(funtionBlockJson);
				}, new JsonTransformer());

		get("/user/:id", "application/json",
				(request, response) -> mUserService.findUserbyUserID(request
						.params(":id")), new JsonTransformer());
		get("/users", "application/json",
				(request, response) -> mUserService.findAllUsers(),
				new JsonTransformer());
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

	}
	

}
