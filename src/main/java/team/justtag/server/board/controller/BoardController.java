package team.justtag.server.board.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import team.justtag.server.board.service.BoardService;
import team.justtag.server.board.service.BoardServiceImpl;
import team.justtag.server.util.JsonTransformer;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class BoardController {

  //  private static final String API_CONTEXT = "/api/v1";

    private final BoardService mBoardServiceImpl;

    public BoardController(BoardServiceImpl boardService) {
        this.mBoardServiceImpl = boardService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post("/boards", "application/json", (request, response) -> {
        	String funtionBlockJson = new String(request.bodyAsBytes(), "UTF-8");
        	mBoardServiceImpl.createNewContents(funtionBlockJson, request.headers("token"));
            response.status(201);
            return response;
        }, new JsonTransformer());
        get("/boards/:id", "application/json", (request, response)
                -> mBoardServiceImpl.find(request.params(":id")), new JsonTransformer());
        get("/boards", "application/json", (request, response)
                -> mBoardServiceImpl.findAll(), new JsonTransformer());
        put("/boards/:id", "application/json", (request, response)
                -> mBoardServiceImpl.update(request.params(":id"), request.body()), new JsonTransformer());
    }


}
