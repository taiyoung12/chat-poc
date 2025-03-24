package us.zep.chatserver.service.chat;

import org.springframework.stereotype.Service;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.redis.RedisPublisher;
import us.zep.chatserver.repository.history.UserRoomHistoryRepository;

@Service
public class ChatMessageProcessor {
	private final RedisPublisher redisPublisher;
	private final UserRoomHistoryRepository userRoomHistoryRepository;

	public ChatMessageProcessor(RedisPublisher redisPublisher, UserRoomHistoryRepository userRoomHistoryRepository) {
		this.redisPublisher = redisPublisher;
		this.userRoomHistoryRepository = userRoomHistoryRepository;
	}

	public void processMessage(ChatMessage chatMessage) {
		if (chatMessage.getRoomId() == null) {
			return;
		}

		redisPublisher.publish(chatMessage);
	}

	public boolean processUserEntry(ChatMessage chatMessage) {
		if (chatMessage.getRoomId() == null || chatMessage.getSender() == null) {
			return false;
		}

		if ("JOIN".equals(chatMessage.getType())) {
			String userId = chatMessage.getSender();
			String roomId = chatMessage.getRoomId();

			if (!userRoomHistoryRepository.hasEnteredRoom(userId, roomId)) {
				userRoomHistoryRepository.recordRoomEntry(userId, roomId);
				redisPublisher.publish(chatMessage);
				return true;
			}
			return false;
		}

		redisPublisher.publish(chatMessage);
		return true;
	}

	public void processUserLeave(ChatMessage chatMessage) {
		if (chatMessage.getRoomId() == null || chatMessage.getSender() == null) {
			return;
		}

		if ("LEAVE".equals(chatMessage.getType())) {
			String userId = chatMessage.getSender();
			String roomId = chatMessage.getRoomId();

			userRoomHistoryRepository.removeRoomEntry(userId, roomId);
		}

		redisPublisher.publish(chatMessage);
	}
}
