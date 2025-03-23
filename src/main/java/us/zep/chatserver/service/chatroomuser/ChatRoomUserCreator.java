package us.zep.chatserver.service.chatroomuser;

import org.springframework.stereotype.Service;

import us.zep.chatserver.controller.chatroom.ChatRoomCreateResponse;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroomuser.ChatRoomUserRepository;

@Service
public class ChatRoomUserCreator {

	private final ChatRoomUserRepository chatRoomUserRepository;

	public ChatRoomUserCreator(ChatRoomUserRepository chatRoomUserRepository) {
		this.chatRoomUserRepository = chatRoomUserRepository;
	}

	public void by(String userId, String roomId){
		chatRoomUserRepository.save(
			userId,
			roomId
		);
	}

}
