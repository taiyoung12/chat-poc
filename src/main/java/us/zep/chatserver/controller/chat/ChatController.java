package us.zep.chatserver.controller.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import us.zep.chatserver.model.ChatMessage;
import us.zep.chatserver.redis.RedisPublisher;
import us.zep.chatserver.service.chat.ChatMessageProcessor;

@Controller
public class ChatController {
	private static final String USER_KEY = "username";

	private final ChatMessageProcessor chatMessageProcessor;


	public ChatController(ChatMessageProcessor chatMessageProcessor) {
		this.chatMessageProcessor = chatMessageProcessor;
	}

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessage chatMessage){
		chatMessageProcessor.processMessage(chatMessage);
	}

	@MessageMapping("/chat.addUser")
	public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
		headerAccessor.getSessionAttributes().put(USER_KEY, chatMessage.getSender());
		chatMessageProcessor.processUserEntry(chatMessage);
	}

	@MessageMapping("/chat.leaveUser")
	public void leaveUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		chatMessageProcessor.processUserLeave(chatMessage, headerAccessor);
	}
}
