package com.junkapa.server.common;

public class Common {

		//security
		public static final int AES_TOKEN_LENGTH = 16; //128bit
		public static final int AES_USER_PASSWORD_LENGTH = 16; //128bit
		public static final int RSA_LENGTH = 2048; // 2048bit
		
		//token
		public static final long EXPIRED_TOKEN_TIME = 21600; // 6시간
		public static final long REFREASH_TOKEN_TIME = 1800; // 30분
		public static final int SESSION_TIME = 1800; // 30분
}
