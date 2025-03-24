package us.zep.chatserver.service.chat;

import static us.zep.chatserver.common.config.websocket.WebSocketConstants.*;
import static us.zep.chatserver.model.ChatMessage.*;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.redis.RedisPublisher;
import us.zep.chatserver.repository.history.UserRoomHistoryRepository;

@Service
public class ChatMessageProcessor {
	private final RedisPublisher redisPublisher;
	private final UserRoomHistoryRepository userRoomHistoryRepository;
	private final SimpMessageSendingOperations messagingTemplate;


	public ChatMessageProcessor(RedisPublisher redisPublisher, UserRoomHistoryRepository userRoomHistoryRepository,
		SimpMessageSendingOperations messagingTemplate) {
		this.redisPublisher = redisPublisher;
		this.userRoomHistoryRepository = userRoomHistoryRepository;
		this.messagingTemplate = messagingTemplate;
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

		if (MessageType.JOIN.equals(chatMessage.getType())) {
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

	public void processUserLeave(
		ChatMessage chatMessage
	) {
		if (chatMessage.getRoomId() == null || chatMessage.getSender() == null) {
			return;
		}

		String userId = chatMessage.getSender();
		String roomId = chatMessage.getRoomId();

		userRoomHistoryRepository.removeRoomEntry(userId, roomId);
		redisPublisher.publish(chatMessage);

		messagingTemplate.convertAndSendToUser(
			userId,
			DISCONNECT_DESTINATION,
			DISCONNECT_PAYLOAD
		);
	}
}
