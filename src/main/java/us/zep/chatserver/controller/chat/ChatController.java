package us.zep.chatserver.controller.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.redis.RedisPublisher;

@Controller
public class ChatController {
	private static final String USER_KEY = "username";

	private final RedisPublisher redisPublisher;

	public ChatController(RedisPublisher redisPublisher) {
		this.redisPublisher = redisPublisher;
	}

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessage chatMessage){
		if(chatMessage.getRoomId() != null){
			redisPublisher.publish(chatMessage);
		}
	}

	@MessageMapping("/chat.addUser")
	public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
		headerAccessor.getSessionAttributes().put(USER_KEY, chatMessage.getSender());
		if(chatMessage.getRoomId() != null) {
			redisPublisher.publish(chatMessage);
		}
	}
}
