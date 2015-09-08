package team.justtag.server.api.rest;

import static spark.Spark.get;

public class HelloWorld {
    public HelloWorld() {
    	// restAPI를 이용한 get test
    	// 사용 예 - http://todoapp-junwookapa.rhcloud.com/hello/준우/25
    	// 결과 - 이름: 준우 나이: 2
    	get("/hello/:name/:age","application/json", (request, response) -> {
    	   // return "이름: " + request.params(":name") +"\n" +"나이: " + request.params(":age");
    	   return request.toString();
    	});
    }
}
