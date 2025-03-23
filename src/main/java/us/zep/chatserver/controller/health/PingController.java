package us.zep.chatserver.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import us.zep.chatserver.common.response.Response;

@RestController
@RequestMapping("/api/v1/ping")
public class PingController {


	@GetMapping
	public Response<String> ping(){
		return Response.success("pong");
	}
}
