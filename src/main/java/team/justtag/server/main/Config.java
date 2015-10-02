package team.justtag.server.main;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.jose4j.jwk.JsonWebKey;

import team.justtag.server.security.JWEwithAES;

public final class Config {
	
	//host
	public static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	public static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;
	public static final String APP_DNS = System.getenv("OPENSHIFT_APP_DNS") != null ? System.getenv("OPENSHIFT_APP_DNS") : IP_ADDRESS+"/"+PORT;

	//security
	public static final String KEY_STRING = "1234567890qwerty"; // 16 wrods
	public static final Key AES_KEY = new SecretKeySpec(KEY_STRING.getBytes(), "AES"); // AES KEY
	public static final int AES_LENGTH = 128;
	public static final int RSA_LENGTH = 2048;
	
	//token
	public static final String EXPIRED_TOKEN_TIME = "21600"; // 6시간
	public static final String REFREASH_TOKEN_TIME = "1800"; // 30분
	public static final int SESSION_TIME = 1800; // 30분
	
}
