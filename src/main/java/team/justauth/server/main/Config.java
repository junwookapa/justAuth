package team.justauth.server.main;

public final class Config {
	
	//host
	public static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	public static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;
	public static final String APP_DNS = System.getenv("OPENSHIFT_APP_DNS") != null ? System.getenv("OPENSHIFT_APP_DNS") : IP_ADDRESS+"/"+PORT;

	//For Linux
	public static final String LOG_FILE_NAME = "justauth.log";
	public static final String LOG_DIR_PATH = System.getenv("OPENSHIFT_LOG_DIR") != null ? System.getenv("OPENSHIFT_LOG_DIR") : "c:\\JustAuth\\";
	
	//security
	public static final int AES_TOKEN_LENGTH = 16; //128bit
	public static final int AES_USER_PASSWORD_LENGTH = 16; //128bit
	public static final int RSA_LENGTH = 2048;
	
	//token
	public static final long EXPIRED_TOKEN_TIME = 21600; // 6시간
	public static final long REFREASH_TOKEN_TIME = 1800; // 30분
	public static final int SESSION_TIME = 1800; // 30분
	
}
