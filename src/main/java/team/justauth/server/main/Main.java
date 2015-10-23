package team.justauth.server.main;

import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;
import team.justauth.server.board.controller.BoardController;
import team.justauth.server.board.service.BoardServiceImpl;
import team.justauth.server.token.controller.TokenController;
import team.justauth.server.token.service.TokenServiceImpl;
import team.justauth.server.user.controller.UserController;
import team.justauth.server.user.service.UserServiceImpl;
import team.justauth.server.util.Log;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Main {
//    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
 //   private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
    	Log.writeLog("logTest");
        setIpAddress(Config.IP_ADDRESS);
        setPort(Config.PORT);
        staticFileLocation("/public");
        
        setController(mongo());
    }

    private static DB mongo() throws Exception {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("justauth");//
        }
        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }
    //
    private static void setController(DB db){
    	new BoardController(new BoardServiceImpl(db));
    	new UserController(new UserServiceImpl(db));
    	new TokenController(new TokenServiceImpl(db));
    	
    }
    
}
