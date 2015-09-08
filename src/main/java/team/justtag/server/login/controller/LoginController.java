package team.justtag.server.login.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import team.justtag.server.login.service.LoginService;
import team.justtag.server.util.JsonTransformer;

public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
		setupEndpoints();
	}

	private void setupEndpoints() {
		// TODO Auto-generated method stub
		post("/login", "application/json", (request, response) -> {
			// String funtionBlockJson = new String(request.bodyAsBytes(),
			// "UTF-8");
				loginService.createNewUser("");
				response.status(201);
				return response;
			}, new JsonTransformer());
		
		get("/login/:id", "application/json", (request, response)
                -> loginService.createNew("asd"), new JsonTransformer());

	}

}
