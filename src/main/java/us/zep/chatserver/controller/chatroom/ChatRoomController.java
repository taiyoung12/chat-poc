package us.zep.chatserver.controller.chatroom;

import static us.zep.chatserver.common.code.ChatRoomCode.*;

import org.springframework.web.bind.annotation.*;

import us.zep.chatserver.common.response.Response;
import us.zep.chatserver.dto.ChatRoomInfo;
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

	@PostMapping("/rooms/join")
	public Response<Void> joinRoom(
		@RequestHeader("User-Id") String userId,
		@RequestBody() String roomId
	){
		chatRoomUserCreator.by(userId, roomId);
		return Response.success(SUCCESS_ADD_USER_TO_ROOM);
	}

    @GetMapping("/rooms")
    public List<ChatRoomInfo> getRooms() {
        return null;
    }
}
