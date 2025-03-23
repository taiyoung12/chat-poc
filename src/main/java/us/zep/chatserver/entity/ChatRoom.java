package us.zep.chatserver.entity;

import java.time.LocalDateTime;

import us.zep.chatserver.common.utils.UUIDGenerator;

public class ChatRoom {
	private String id;
	private String name;
	private LocalDateTime createdAt;

	public ChatRoom() {
		this.id = UUIDGenerator.generate();
		this.createdAt = LocalDateTime.now();
	}

	public ChatRoom(String name) {
		this.id = UUIDGenerator.generate();
		this.name = name;
		this.createdAt = LocalDateTime.now();
	}

	public String getName(){
		return name;
	}
}