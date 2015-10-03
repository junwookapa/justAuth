package team.justtag.server.token.controller;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import team.justtag.server.security.JWEUtil;
import team.justtag.server.token.service.TokenServiceImpl;
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
					System.out.println(statusCode+"");
					if(statusCode == 201){
						String decodingString = new JWEUtil().decoder(request.session().attribute("privateKey"), request.body());
						response.body(mTokenService.issueToken(decodingString, request.host()));
					}else{
						response.body();					
						}
				}
		);
		get("/token/:token", "application/json", (request, response) ->{
			return mTokenService.verifyandRefresh(request.params(":token"), request.host());
			},
		new JsonTransformer());
		// 토큰 재발급
		put("/token/:token", "application/json", (request, response) ->{
			return mTokenService.issueToken(request.params(":token"), request.host());
			},
		new JsonTransformer());
		// 토큰 삭제
		delete("/token/:token", "application/json", (request, response) -> {
					return mTokenService.deleteToken(request.params(":token"));
					},
				new JsonTransformer());
		
		
		before("/user", "application/json", (request, response) ->{
			String key =null;
			if(request.headers().contains("token")){
				key = request.headers("token");
			}else{
				response.status(204);
			}
			switch(mTokenService.verifyToken(key, request.host())){
				default:
					break;
				case success:
					break;
				case notFoundToken:
				case tokenExpired:
				case tokenExpiringsoon:
				case tokenUpdateFail:
				case unknownError:
					response.status(204);
			}
			});
		before("/users", "application/json", (request, response) ->{
			String key =null;
			if(request.headers().contains("token")){
				key = request.headers("token");
			}else{
				response.status(204);
			}
			switch(mTokenService.verifyToken(key, request.host())){
				default:
					break;
				case success:
					break;
				case notFoundToken:
				case tokenExpired:
				case tokenExpiringsoon:
				case tokenUpdateFail:
				case unknownError:
					response.status(204);
			}
			});
		
		
	}

}
