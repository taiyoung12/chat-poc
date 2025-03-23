package us.zep.chatserver.repository.chatroomuser;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import us.zep.chatserver.entity.UserChatRoom;

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
}
