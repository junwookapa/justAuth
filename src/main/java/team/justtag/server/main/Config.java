package team.justtag.server.main;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public final class Config {
	
	//host
	public static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	public static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;
	public static final String APP_DNS = System.getenv("OPENSHIFT_APP_DNS") != null ? System.getenv("OPENSHIFT_APP_DNS") : IP_ADDRESS+"/"+PORT;

	//security
	public static final String AES_TOKEN = "1234567890qwerty";
	public static final Key SECRET_KEY = new SecretKeySpec(AES_TOKEN.getBytes(), "AES");
	
	//token
	public static final String EXPIRED_TOKEN_TIME = "21600"; // 6시간
	public static final String REFREASH_TOKEN_TIME = "1800"; // 30분
	

}
