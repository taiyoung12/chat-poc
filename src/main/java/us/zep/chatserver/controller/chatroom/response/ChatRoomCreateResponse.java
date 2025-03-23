package us.zep.chatserver.controller.chatroom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomCreateResponse {
	private String id;
	private String name;
}
