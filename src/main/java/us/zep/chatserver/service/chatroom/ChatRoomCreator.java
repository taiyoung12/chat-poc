package us.zep.chatserver.service.chatroom;

import org.springframework.stereotype.Service;

import us.zep.chatserver.controller.chatroom.ChatRoomCreateResponse;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroom.ChatRoomRepository;

@Service
public class ChatRoomCreator {
	private final ChatRoomRepository chatRoomRepository;

	public ChatRoomCreator(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}

	public ChatRoomCreateResponse by(String name){
		ChatRoom chatRoom = chatRoomRepository.save(name);
		return new ChatRoomCreateResponse(
			chatRoom.getId(),
			chatRoom.getName()
		);
	}
}
