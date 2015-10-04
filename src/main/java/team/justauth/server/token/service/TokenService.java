
package team.justauth.server.token.service;
import team.justauth.server.main.Status.TokenStatus;

public interface TokenService {
	
	public String issueToken(String body, String aud); //토큰 발급
	public TokenStatus verifyToken(String token, String aud); // 토큰 확인
	public String verifyandRefresh(String token, String aud); // 토큰 확인
	public TokenStatus deleteToken(String token); // 토큰 삭제
	public TokenStatus isUpdateToken(long expireTime); // 토큰을 갱신해야하는가
	public TokenStatus isExpiredToken(long expireTime); // 토큰이 만료되었는가
	
}
