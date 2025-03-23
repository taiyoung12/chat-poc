package us.zep.chatserver.repository.chatroomuser;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import us.zep.chatserver.entity.UserChatRoom;

@Repository
public class ChatRoomUserRepository {
	private final Set<UserChatRoom> userChatRooms = ConcurrentHashMap.newKeySet();

	public void save(String userId, String chatRoomId) {
		Optional<UserChatRoom> existing = findByUserIdAndChatRoomId(userId, chatRoomId);

		if (existing.isPresent()) {
			return;
		}

		UserChatRoom userChatRoom = new UserChatRoom(userId, chatRoomId);
		userChatRooms.add(userChatRoom);
	}

	public Optional<UserChatRoom> findByUserIdAndChatRoomId(String userId, String chatRoomId) {
		return userChatRooms.stream()
			.filter(userChatRoom-> userChatRoom.getUserId().equals(userId) && userChatRoom.getChatRoomId().equals(chatRoomId))
			.findFirst();
	}

	public List<UserChatRoom> findByChatRoomId(String chatRoomId) {
		return userChatRooms.stream()
			.filter(ucr -> ucr.getChatRoomId().equals(chatRoomId))
			.collect(Collectors.toList());
	}

	public List<UserChatRoom> findByUserId(String userId) {
		return userChatRooms.stream()
			.filter(ucr -> ucr.getUserId().equals(userId))
			.collect(Collectors.toList());
	}
}
