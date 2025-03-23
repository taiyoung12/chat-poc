package us.zep.chatserver.controller.chatroom;

import org.springframework.web.bind.annotation.*;

import us.zep.chatserver.common.response.Response;
import us.zep.chatserver.dto.ChatRoomInfo;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.service.chatroom.ChatRoomCreator;
import us.zep.chatserver.service.chatroomuser.ChatRoomUserCreator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatRoomController {
    private final ChatRoomCreator chatRoomCreator;
	private final ChatRoomUserCreator chatRoomUserCreator;

	public ChatRoomController(ChatRoomCreator chatRoomCreator, ChatRoomUserCreator chatRoomUserCreator) {
		this.chatRoomCreator = chatRoomCreator;
		this.chatRoomUserCreator = chatRoomUserCreator;
	}

    @PostMapping("/rooms")
    public Response<ChatRoomCreateResponse> createRoom(
		@RequestHeader("User-Id") String userId,
		@RequestParam String name
	) {
		ChatRoomCreateResponse response = chatRoomCreator.by(name);
		chatRoomUserCreator.by(userId, response.getId());
        return Response.success(response);
    }

    @GetMapping("/rooms")
    public List<ChatRoomInfo> getRooms() {
        return null;
    }
}
