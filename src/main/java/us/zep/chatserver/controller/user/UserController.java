package us.zep.chatserver.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.zep.chatserver.common.response.Response;
import us.zep.chatserver.controller.user.request.UserCreateRequest;
import us.zep.chatserver.controller.user.response.UserCreateResponse;
import us.zep.chatserver.service.user.UserCreator;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	private final UserCreator userCreator;

	public UserController(UserCreator userCreator) {
		this.userCreator = userCreator;
	}

	@PostMapping
	public Response<UserCreateResponse> addUser(
		@RequestBody UserCreateRequest userCreateRequest
	){
		UserCreateResponse response = userCreator.addUser(userCreateRequest);
		return Response.success(response);
	}
}
