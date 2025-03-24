package us.zep.chatserver.redis;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.zep.chatserver.model.ChatMessage;

@ExtendWith(MockitoExtension.class)
public class RedisPublisherTest {

	@Mock
	private RedisTemplate<String, Object> redisTemplate;

	@Mock
	private ChannelTopic channelTopic;

	private RedisPublisher sut;

	@BeforeEach
	void setUp() {
		sut = new RedisPublisher(redisTemplate, channelTopic);
	}

	@Test
	void Chat_Message를_publish_할_수_있다() {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setSender("testUser");
		chatMessage.setContent("Hello, Redis!");
		chatMessage.setRoomId("room1");

		String topicName = "chat:room:room1";
		when(channelTopic.getTopic()).thenReturn(topicName);

		sut.publish(chatMessage);

		verify(redisTemplate, times(1)).convertAndSend(eq(topicName), anyString());
	}

	@Test
	void Chat_Message를_2번_publish_할_수_있다() {
		ChatMessage message1 = new ChatMessage();
		message1.setSender("user1");
		message1.setContent("Message 1");
		message1.setRoomId("room1");

		ChatMessage message2 = new ChatMessage();
		message2.setSender("user2");
		message2.setContent("Message 2");
		message2.setRoomId("room1");

		String topicName = "chat:room:room1";
		when(channelTopic.getTopic()).thenReturn(topicName);

		sut.publish(message1);
		sut.publish(message2);

		verify(redisTemplate, times(2)).convertAndSend(eq(topicName), anyString());
	}
}