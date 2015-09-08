package team.justtag.server.login.service;

import java.util.Date;

import team.justtag.server.login.vo.User;
import team.justtag.server.ui.todo.vo.Todo;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class LoginService {
	private final DB db;
    private final DBCollection collection;

    public LoginService(DB db) {
        this.db = db;
        this.collection = db.getCollection("Login");
    }
    public void createNewUser(String body) {
        User user = new Gson().fromJson(body, User.class);
        //collection.insert(new BasicDBObject("user", user.getUser()).append("password", user.getPassword()).append("createdOn", new Date()));
        collection.insert(new BasicDBObject("user", "아리옹").append("password", "하리옹").append("createdOn", new Date()));
    }

}
