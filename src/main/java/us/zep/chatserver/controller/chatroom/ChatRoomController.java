package us.zep.chatserver.controller.chatroom;

import static us.zep.chatserver.common.code.ChatRoomCode.*;

import org.springframework.web.bind.annotation.*;

import us.zep.chatserver.common.response.Response;
import us.zep.chatserver.controller.chatroom.request.RoomIdRequest;
import us.zep.chatserver.controller.chatroom.response.ChatRoomCreateResponse;
import us.zep.chatserver.service.chatroom.ChatRoomCreator;
import us.zep.chatserver.service.chatroom.ChatRoomReader;
import us.zep.chatserver.service.chatroomuser.ChatRoomUserCreator;
import us.zep.chatserver.service.chatroomuser.ChatRoomUserReader;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatRoomController {
    private final ChatRoomCreator chatRoomCreator;
	private final ChatRoomUserCreator chatRoomUserCreator;

	private final ChatRoomUserReader chatRoomUserReader;

	private final ChatRoomReader chatRoomReader;

	public ChatRoomController(ChatRoomCreator chatRoomCreator, ChatRoomUserCreator chatRoomUserCreator,
		ChatRoomUserReader chatRoomUserReader, ChatRoomReader chatRoomReader) {
		this.chatRoomCreator = chatRoomCreator;
		this.chatRoomUserCreator = chatRoomUserCreator;
		this.chatRoomUserReader = chatRoomUserReader;
		this.chatRoomReader = chatRoomReader;
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
		@RequestBody RoomIdRequest roomIdRequest
	){
		chatRoomUserCreator.by(userId, roomIdRequest.getRoomId());
		return Response.success(SUCCESS_ADD_USER_TO_ROOM);
	}

    @GetMapping("/rooms")
    public Response<List<String>> getUserIdsBy(
		@RequestParam String roomId
	) {
		List<String> response = chatRoomUserReader.findByRoomId(roomId);
        return Response.success(response);
    }

	@GetMapping("/rooms/me")
	public Response<List<String>> getRoomsId(
		@RequestHeader("User-Id") String userId
	) {
		List<String> roomIds = chatRoomUserReader.findByUserId(userId);
		List<String> response = chatRoomReader.findBy(roomIds);
		return Response.success(response);
	}
}
