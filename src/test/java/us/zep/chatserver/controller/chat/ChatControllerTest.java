package us.zep.chatserver.controller.chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.redis.RedisPublisher;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatControllerTest {

	@Mock
	private RedisPublisher redisPublisher;

	@Mock
	private SimpMessagingTemplate messagingTemplate;

	@Mock
	private SimpMessageHeaderAccessor headerAccessor;

	@InjectMocks
	private ChatController chatController;

	private ChatMessage chatMessage;
	private Map<String, Object> sessionAttributes;

	@BeforeEach
	void setUp() {
		chatMessage = new ChatMessage();
		chatMessage.setType(ChatMessage.MessageType.CHAT);
		chatMessage.setSender("testUser");
		chatMessage.setContent("Hello, World!");
		chatMessage.setRoomId("room123");
	}

	@Test
	void 채팅_메시지_전송시_RedisPublisher가_호출된다() {
		chatController.sendMessage(chatMessage);

		verify(redisPublisher, times(1)).publish(chatMessage);
	}

	@Test
	void 채팅방_ID가_null이면_RedisPublisher가_호출되지_않는다() {
		chatMessage.setRoomId(null);
		chatController.sendMessage(chatMessage);

		verify(redisPublisher, times(0)).publish(any(ChatMessage.class));
	}

	@Test
	void 사용자_추가시_세션에_사용자명이_저장된다() {
		Map<String, Object> sessionAttributes = new HashMap<>();
		when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

		chatController.addUser(chatMessage, headerAccessor);

		verify(headerAccessor, times(1)).getSessionAttributes();
		assert sessionAttributes.get("username").equals("testUser");
	}

	@Test
	void 사용자_추가시_RedisPublisher가_호출된다() {
		chatController.addUser(chatMessage, headerAccessor);

		verify(redisPublisher, times(1)).publish(chatMessage);
	}

	@Test
	void 사용자_추가시_채팅방_ID가_null이면_RedisPublisher가_호출되지_않는다() {
		chatMessage.setRoomId(null);

		chatController.addUser(chatMessage, headerAccessor);

		verify(redisPublisher, times(0)).publish(any(ChatMessage.class));
	}
}