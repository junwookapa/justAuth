package team.justauth.server.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import team.justauth.server.main.Config;

public class Log {
	
	public static String getOSType(){
		System.out.println(System.getProperty("os.name").toLowerCase()+"");
		if(System.getProperty("os.name").toLowerCase().contains("linux")){
			return "home\\justAuth\\";
		}else if(System.getProperty("os.name").toLowerCase().contains("window")){
			return "c:\\JustAuth\\";
		}else{
			return null;
		}

	}
	
	public static void writeLog(String log) {
		try {
			File file = new File(Config.LOG_DIR_PATH + Config.LOG_FILE_NAME);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter out = new BufferedWriter(osw);

			out.write(log);
			out.newLine();
			out.close();
		} catch (IOException e) {
			System.err.println("Fail to write log message" + e);
			System.exit(1);
		}
	}
	  
}

