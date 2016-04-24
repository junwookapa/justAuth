package team.justauth.server.token.controller;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;

import org.eclipse.jetty.http.HttpTester.Request;

import team.justauth.server.security.JWEUtil;
import team.justauth.server.token.service.TokenService;
import team.justauth.server.token.service.TokenServiceImpl;
import team.justauth.server.util.JsonTransformer;


public class TokenController {

	private final TokenServiceImpl mTokenService;

	public TokenController(TokenServiceImpl tokenService) {
		this.mTokenService = tokenService;
		setupEndpoints();
	}

	private void setupEndpoints() throws NullPointerException{
		//토큰 발급 및 로그인
		after("/login", "application/json",
				(request, response) ->{
					int statusCode = response.raw().getStatus();
					if(statusCode == 201){
						String decodingString = JWEUtil.decoder(request.session().attribute("privateKey"), request.body());
						response.body(mTokenService.issueToken(decodingString, request.ip()));
					}else{
						response.status(201);
					}
				}
		);
		//토큰 확인
		get("/token/:token", "application/json", (request, response) ->{
			return mTokenService.verifyandRefresh(request.params(":token"), request.ip());
			},
		new JsonTransformer());
		
		before("/token/:token", "application/json", (request, response) ->{
			switch((mTokenService.verifyToken(request.params(":token"), request.ip()))){
				case success:
					break;
				default:
				case notFoundToken:
				case tokenExpired:
				case tokenExpiringsoon:
				case tokenUpdateFail: 
				case unknownError:
			}
			
			});
		//eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.UB7ERDL7RJREO5deK3ZDSHtXGSndqCp3AKIWuS6XXaUp80AFchy-Tg.X_TP5eCEVmvlkqGyQO6ugQ.cN74vpvVN7u1hvHwsERw14ilNdBpbaYxrOW1q-EKw2jl3ju_1KatgnOQKgFWUl_v1WA4fFnvY0TUAkZgG11LVn7wTEW1AMOcyJMJZxeAOIFTS7XHUz20T5GgoMwd4rAtYn2RwHMgmkx9-yVgJtsA61wuyjhATQjNx0ey2a8MXLY.zu_ZzqaocBXWHyFCsT4rlg
		// 토큰 재발급
		put("/token/:token", "application/json", (request, response) ->{
			return mTokenService.tokenUpdate(request.params(":token"), request.ip());
			},
		new JsonTransformer());
		// 토큰 삭제
		delete("/token/:token", "application/json", (request, response) -> {
					return mTokenService.deleteToken(request.params(":token"));
					},
				new JsonTransformer());
			
		before("/user", "application/json", (request, response) -> {
			String key = null;
			if (request.headers().contains("token")) {
				key = request.headers("token");
				try {
					
					response.header("user_id", mTokenService.getUserID(key));
				} catch (NullPointerException e) {

				}
			} else {
				response.status(204);
			}
			switch (mTokenService.verifyToken(key, request.ip())) {
			case success:
				break;
			default:
			case notFoundToken:
			case tokenExpired:
			case tokenExpiringsoon:
			case tokenUpdateFail:
			case unknownError:
				response.status(204);
				break;
			}
		});
				
		before("/users", "application/json", (request, response) -> {
			String key = null;
			if (request.headers().contains("token")) {
				key = request.headers("token");
				try {
					response.header("user_id", mTokenService.getUserID(key));
				} catch (NullPointerException e) {

				}
			} else {
				response.status(204);
			}
			switch (mTokenService.verifyToken(key, request.ip())) {
			case success:
				break;
			default:
			case notFoundToken:
			case tokenExpired:
			case tokenExpiringsoon:
			case tokenUpdateFail:
			case unknownError:
				response.status(204);
				break;
			}
		});
		
		
	}

}
