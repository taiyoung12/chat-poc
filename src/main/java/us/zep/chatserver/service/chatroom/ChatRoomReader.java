package us.zep.chatserver.service.chatroom;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroom.ChatRoomRepository;

@Service
public class ChatRoomReader {
	private final ChatRoomRepository chatRoomRepository;

	public ChatRoomReader(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}

	public List<String> findBy(List<String> roomIds){
		return chatRoomRepository.findBy(roomIds)
			.stream()
			.map(ChatRoom::getName)
			.collect(Collectors.toList());
	}
}
