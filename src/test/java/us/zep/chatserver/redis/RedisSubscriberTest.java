package us.zep.chatserver.redis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.zep.chatserver.common.exception.BaseException;
import us.zep.chatserver.model.ChatMessage;

@ExtendWith(MockitoExtension.class)
public class RedisSubscriberTest {

	@Mock
	private RedisTemplate<String, Object> redisTemplate;

	@Mock
	private SimpMessagingTemplate messagingTemplate;

	@Mock
	private Message message;

	@Mock
	private StringRedisSerializer stringRedisSerializer;

	private RedisSubscriber sut;
	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		when(redisTemplate.getStringSerializer()).thenReturn(stringRedisSerializer);
		sut = new RedisSubscriber(redisTemplate, messagingTemplate);
	}

	@Test
	void redis에서_메시지를_수신할_수_있다() throws JsonProcessingException {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setSender("테스트사용자");
		chatMessage.setContent("안녕하세요!");
		chatMessage.setRoomId("단체방");

		String messageJson = objectMapper.writeValueAsString(chatMessage);
		byte[] messageBytes = messageJson.getBytes();
		byte[] patternBytes = "chat:room:room123".getBytes();

		when(message.getBody()).thenReturn(messageBytes);
		when(stringRedisSerializer.deserialize(messageBytes)).thenReturn(messageJson);

		sut.onMessage(message, patternBytes);

		verify(redisTemplate, times(1)).getStringSerializer();
		verify(stringRedisSerializer, times(1)).deserialize(messageBytes);
		verify(messagingTemplate, times(1)).convertAndSend("/topic/room/" + chatMessage.getRoomId(), chatMessage);
	}

	@Test
	void 잘못된_형식의_메시지를_수신하면_예외를_처리할_수_있다() {
		String invalidJson = "{invalid-json}";
		byte[] messageBytes = invalidJson.getBytes();

		when(message.getBody()).thenReturn(messageBytes);
		when(stringRedisSerializer.deserialize(messageBytes)).thenReturn(invalidJson);

		assertThrows(BaseException.class, () -> {
			sut.onMessage(message, new byte[0]);
		});
	}

	@Test
	void 여러_채팅방_메시지를_각각_올바른_주제로_전송할_수_있다() throws JsonProcessingException {
		ChatMessage chatMessage1 = new ChatMessage();
		chatMessage1.setSender("사용자1");
		chatMessage1.setContent("첫번째 방 메시지");
		chatMessage1.setRoomId("room1");

		ChatMessage chatMessage2 = new ChatMessage();
		chatMessage2.setSender("사용자2");
		chatMessage2.setContent("두번째 방 메시지");
		chatMessage2.setRoomId("room2");

		String messageJson1 = objectMapper.writeValueAsString(chatMessage1);
		String messageJson2 = objectMapper.writeValueAsString(chatMessage2);
		byte[] messageBytes1 = messageJson1.getBytes();
		byte[] messageBytes2 = messageJson2.getBytes();
		byte[] patternBytes = "chat:room:*".getBytes();

		when(message.getBody()).thenReturn(messageBytes1);
		when(stringRedisSerializer.deserialize(messageBytes1)).thenReturn(messageJson1);
		sut.onMessage(message, patternBytes);

		verify(messagingTemplate, times(1)).convertAndSend("/topic/room/room1", chatMessage1);

		when(message.getBody()).thenReturn(messageBytes2);
		when(stringRedisSerializer.deserialize(messageBytes2)).thenReturn(messageJson2);

		sut.onMessage(message, patternBytes);
		verify(messagingTemplate, times(1)).convertAndSend("/topic/room/room2", chatMessage2);
	}

	@Test
	void null_메시지_본문을_수신하면_예외를_처리할_수_있다() {
		byte[] patternBytes = "chat:room:room123".getBytes();

		when(message.getBody()).thenReturn(null);
		when(stringRedisSerializer.deserialize(null)).thenReturn(null);

		assertThrows(BaseException.class, () -> {
			sut.onMessage(message, patternBytes);
		});
	}
}