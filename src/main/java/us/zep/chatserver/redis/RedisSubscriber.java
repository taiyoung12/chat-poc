package us.zep.chatserver.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import us.zep.chatserver.model.ChatMessage;

@Component
public class RedisSubscriber implements MessageListener {
	private final RedisTemplate<String, Object> redisTemplate;
	private final SimpMessagingTemplate messagingTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public RedisSubscriber(RedisTemplate redisTemplate, SimpMessagingTemplate messagingTemplate) {
		this.redisTemplate = redisTemplate;
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			String body = redisTemplate.getStringSerializer().deserialize(message.getBody());

			ChatMessage chatMessage = objectMapper.readValue(body, ChatMessage.class);
			messagingTemplate.convertAndSend("/topic/room/" + chatMessage.getRoomId(), chatMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
