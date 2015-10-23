package team.justauth.server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import team.justauth.server.main.Config;

public class Log {
	
	public static void getOSType(){
		
	}
	
	public static void writeLog(String log){
	try {
	      BufferedWriter out = new BufferedWriter(new FileWriter(Config.LOG_DIR_PATH+Config.LOG_FILE_NAME));

	      out.write(log); out.newLine();

	      out.close();
	    } catch (IOException e) {
	        System.err.println("Fail to write log message"+e);
	        System.exit(1);
	    }
	}
	  
}

