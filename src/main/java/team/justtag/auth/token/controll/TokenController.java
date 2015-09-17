package team.justtag.auth.token.controll;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import team.justtag.auth.token.service.TokenServiceImpl;
import team.justtag.server.util.JsonTransformer;

public class TokenController {

	private final TokenServiceImpl mTokenService;

	public TokenController(TokenServiceImpl tokenService) {
		this.mTokenService = tokenService;
		setupEndpoints();
	}

	private void setupEndpoints() throws NullPointerException{
		//토큰 발급
		after("/login", "application/json",
				(request, response) ->{
					int statusCode = response.raw().getStatus();
					if(statusCode == 201){
						String funtionBlockJson = new String(request.bodyAsBytes(), "UTF-8");
						response.body(mTokenService.issueToken(funtionBlockJson, request.host()));
					}else{
						response.body();					}
		
				}
		);
		// 토큰 검증
		get("/token/:token", "application/json", (request, response) ->
					mTokenService.verifyToken(request.params(":token"), request.host()),
				new JsonTransformer());
		// 토큰 업데이트
		put("/token/:token", "application/json", (request, response) -> 
		mTokenService.updateToken(request.params(":token"), request.host()),
				new JsonTransformer());
		// 토큰 삭제
		delete("/token/:token", "application/json", (request,
				response) -> {
					mTokenService.deleteToken(request.params(":token"));
					response.status(201);
					return response;
					},
				new JsonTransformer());	
	}

}
