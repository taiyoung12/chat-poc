package us.zep.chatserver.service.chatroom;

import org.springframework.stereotype.Service;

import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.ChatRoomRepository;

@Service
public class ChatRoomCreator {
	private final ChatRoomRepository chatRoomRepository;

	public ChatRoomCreator(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}

	public ChatRoom by(String name){
		return chatRoomRepository.createRoom(name);
	}
}
