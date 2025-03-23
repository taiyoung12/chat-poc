package us.zep.chatserver.repository.chatroom;

import static us.zep.chatserver.common.code.ChatRoomCode.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import us.zep.chatserver.common.exception.BaseException;
import us.zep.chatserver.entity.ChatRoom;

@Repository
public class ChatRoomRepository {
	private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

	public ChatRoom save(String name){
		if (name.isEmpty() || name.isBlank()){
			throw new BaseException(INVALID_ROOM_NAME_INPUT);
		}

		ChatRoom chatRoom = new ChatRoom(name);
		chatRooms.put(chatRoom.getId(), chatRoom);

		return chatRoom;
	}
}
