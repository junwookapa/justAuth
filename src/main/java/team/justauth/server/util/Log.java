package team.justauth.server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private static final String LOG_PATH = System.getenv("OPENSHIFT_LOG_DIR") != null ? System.getenv("OPENSHIFT_LOG_DIR") : "c:/Test/";
	public static void writeLog(){
	try {
	      ////////////////////////////////////////////////////////////////
	      BufferedWriter out = new BufferedWriter(new FileWriter(LOG_PATH+"out.txt"));
	      String s = "출력 파일에 저장될 이런 저런 문자열입니다.";

	      out.write(s); out.newLine();
	      out.write(s); out.newLine();

	      out.close();
	      ////////////////////////////////////////////////////////////////
	    } catch (IOException e) {
	        System.err.println(e); // 에러가 있다면 메시지 출력
	        System.exit(1);
	    }
	}
	  
}

