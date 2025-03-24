package us.zep.chatserver.service.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.Principal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.model.ChatMessage.MessageType;
import us.zep.chatserver.redis.RedisPublisher;
import us.zep.chatserver.repository.history.UserRoomHistoryRepository;

@ExtendWith(MockitoExtension.class)
public class ChatMessageProcessorTest {

	@Mock
	private RedisPublisher redisPublisher;

	@Mock
	private UserRoomHistoryRepository userRoomHistoryRepository;

	@Mock
	private SimpMessageSendingOperations messagingTemplate;

	@Mock
	private SimpMessageHeaderAccessor headerAccessor;


	@Mock
	private Principal principal;


	private ChatMessageProcessor sut;

	private ChatMessage chatMessage;

	@BeforeEach
	void setUp() {
		sut = new ChatMessageProcessor(
			redisPublisher,
			userRoomHistoryRepository,
			messagingTemplate
		);

		chatMessage = new ChatMessage();
		chatMessage.setSender("testUser");
		chatMessage.setContent("Hello, World!");
		chatMessage.setRoomId("room123");
	}

	@Test
	void 일반_채팅_메시지_처리시_RedisPublisher가_호출될_수_있다() {
		chatMessage.setType(MessageType.CHAT);

		sut.processMessage(chatMessage);

		verify(redisPublisher, times(1)).publish(chatMessage);
	}

	@Test
	void 채팅방_id가_null이면_RedisPublisher가_호출되지_않을_수_있다() {
		chatMessage.setType(MessageType.CHAT);
		chatMessage.setRoomId(null);

		sut.processMessage(chatMessage);

		verify(redisPublisher, never()).publish(any(ChatMessage.class));
	}

	@Test
	void 사용자_첫_입장시_입장_이력이_저장되고_메시지가_발행될_수_있다() {
		chatMessage.setType(MessageType.JOIN);

		when(userRoomHistoryRepository.hasEnteredRoom("testUser", "room123")).thenReturn(false);

		sut.processUserEntry(chatMessage);

		verify(userRoomHistoryRepository, times(1)).recordRoomEntry("testUser", "room123");
		verify(redisPublisher, times(1)).publish(chatMessage);
	}

	@Test
	void 사용자_재입장시_메시지가_발행되지_않을_수_있다() {
		chatMessage.setType(MessageType.JOIN);

		when(userRoomHistoryRepository.hasEnteredRoom("testUser", "room123")).thenReturn(true);

		boolean result = sut.processUserEntry(chatMessage);

		assertFalse(result);
		verify(userRoomHistoryRepository, never()).recordRoomEntry(anyString(), anyString());
		verify(redisPublisher, never()).publish(any(ChatMessage.class));
	}

	@Test
	void 사용자_퇴장시_입장_이력이_삭제되고_메시지가_발행될_수_있다() {
		chatMessage.setType(MessageType.LEAVE);
		when(headerAccessor.getUser()).thenReturn(principal);
		when(principal.getName()).thenReturn("testUser");

		sut.processUserLeave(chatMessage, headerAccessor);

		verify(userRoomHistoryRepository, times(1)).removeRoomEntry("testUser", "room123");
		verify(redisPublisher, times(1)).publish(chatMessage);
		verify(messagingTemplate).convertAndSendToUser("testUser", "/queue/disconnect", "DISCONNECT");
	}

	@Test
	void 사용자_ID가_null이면_퇴장_이력이_삭제되지_않을_수_있다() {
		chatMessage.setType(MessageType.LEAVE);
		chatMessage.setSender(null);

		sut.processUserLeave(chatMessage, headerAccessor);

		verify(userRoomHistoryRepository, never()).removeRoomEntry(anyString(), anyString());
	}

	@Test
	void 채팅방_id가_null이면_퇴장_이력이_삭제되지_않을_수_있다() {
		chatMessage.setType(MessageType.LEAVE);
		chatMessage.setRoomId(null);

		sut.processUserLeave(chatMessage, headerAccessor);

		verify(userRoomHistoryRepository, never()).removeRoomEntry(anyString(), anyString());
	}
}