package team.justtag.server.todo.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import team.justtag.server.todo.service.TodoService;
import team.justtag.server.util.JsonTransformer;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class TodoResource {

  //  private static final String API_CONTEXT = "/api/v1";

    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post("/todos", "application/json", (request, response) -> {
        	String funtionBlockJson = new String(request.bodyAsBytes(), "UTF-8");
            todoService.createNewTodo(funtionBlockJson);
            response.status(201);
            
            return response;
        }, new JsonTransformer());

        get("/todos/:id", "application/json", (request, response)

                -> todoService.find(request.params(":id")), new JsonTransformer());

        get("/todos", "application/json", (request, response)

                -> todoService.findAll(), new JsonTransformer());

        put("/todos/:id", "application/json", (request, response)

                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());
    }


}
