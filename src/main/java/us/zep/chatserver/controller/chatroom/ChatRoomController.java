package us.zep.chatserver.controller.chatroom;

import org.springframework.web.bind.annotation.*;

import us.zep.chatserver.common.response.Response;
import us.zep.chatserver.dto.ChatRoomInfo;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.service.chatroom.ChatRoomCreator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatRoomController {
    private final ChatRoomCreator chatRoomCreator;

	public ChatRoomController(ChatRoomCreator chatRoomCreator) {
		this.chatRoomCreator = chatRoomCreator;
	}

    @PostMapping("/rooms")
    public Response<ChatRoomCreateResponse> createRoom(@RequestParam String name) {
		ChatRoomCreateResponse response = chatRoomCreator.by(name);
        return Response.success(response);
    }

    @GetMapping("/rooms")
    public List<ChatRoomInfo> getRooms() {
        return null;
    }
}
