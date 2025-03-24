package us.zep.chatserver.controller.chatroom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomReadReponse {
	private String roomId;
	private String name;
}
