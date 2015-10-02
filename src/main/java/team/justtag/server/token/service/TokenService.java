
package team.justtag.server.token.service;
import team.justtag.server.main.Status.TokenStatus;

public interface TokenService {
	
	public String issueToken(String body, String aud); //토큰 발급
	public String updateToken(String token, String aud); // 토큰 업데이트
	public String verifyToken(String token, String aud); // 토큰 확인
	public TokenStatus deleteToken(String token); // 토큰 삭제
	
	// private TokenStatus isUpdateToken(long expireTime); // 토큰을 업데이트 해야하는가?
	// private TokenStatus isExpiredToken(long expireTime); //토큰이 만료되었는가?
	// private TokenStatus isUserExist(String userID); // 사용자가 존재하는가?

}
