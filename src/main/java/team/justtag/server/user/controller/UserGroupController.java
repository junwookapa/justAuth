package team.justtag.server.user.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import team.justtag.server.user.service.UserGroupService;
import team.justtag.server.util.JsonTransformer;

public class UserGroupController {

	private UserGroupService mUserGroupService;

	public UserGroupController(UserGroupService userGroupService) {

		this.mUserGroupService = userGroupService;
		setupEndpoints();
	}

	private void setupEndpoints() {
		
		//user group
				post("/userGroup", "application/json", (request, response) -> {
					return null;
					// create UserGroup
					}, new JsonTransformer());
				get("/userGroup/:id", "application/json", (request, response) -> {
					return null;
					// get UserGroup
					}, new JsonTransformer());
				put("/userGroup/:id", "application/json", (request, response) -> {
					return null;
					// update UserGroup
					}, new JsonTransformer());
				delete("/userGroup/:id", "application/json", (request, response) -> {
					return null;
					// update UserGroup
					}, new JsonTransformer());

	}
	

}


