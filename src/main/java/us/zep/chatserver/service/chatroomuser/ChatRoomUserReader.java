package us.zep.chatserver.service.chatroomuser;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import us.zep.chatserver.entity.UserChatRoom;
import us.zep.chatserver.repository.chatroomuser.ChatRoomUserRepository;

@Service
public class ChatRoomUserReader {
	private final ChatRoomUserRepository chatRoomUserRepository;

	public ChatRoomUserReader(ChatRoomUserRepository chatRoomUserRepository) {
		this.chatRoomUserRepository = chatRoomUserRepository;
	}

	public List<String> findByRoomId(String roomId){
		List<UserChatRoom> userChatRooms = chatRoomUserRepository.findByChatRoomId(roomId);

		return userChatRooms.stream()
			.map(UserChatRoom::getUserId)
			.collect(Collectors.toList());
	}
}
