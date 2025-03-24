package us.zep.chatserver.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.zep.chatserver.model.ChatMessage;

@Component
public class RedisPublisher {
	private final RedisTemplate<String, Object> redisTemplate;
	private final ChannelTopic topic;

	private final ObjectMapper objectMapper = new ObjectMapper();
	public RedisPublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	public void publish(ChatMessage message){
		try{
			String json = objectMapper.writeValueAsString(message);
			redisTemplate.convertAndSend(topic.getTopic(), json);
		}catch (JsonProcessingException e){
			e.printStackTrace();
		}
	}

}
