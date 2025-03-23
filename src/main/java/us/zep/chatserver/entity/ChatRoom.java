package us.zep.chatserver.entity;

import java.time.LocalDateTime;

import us.zep.chatserver.common.utils.UUIDGenerator;

public class ChatRoom {
	private String id;
	private String roomName;
	private LocalDateTime createdAt;

	public ChatRoom() {
		this.id = generateId();
		this.createdAt = LocalDateTime.now();
	}

	public ChatRoom(String roomName) {
		this.id = generateId();
		this.roomName = roomName;
		this.createdAt = LocalDateTime.now();
	}

	public String getName(){
		return roomName;
	}

	private String generateId(){
		return UUIDGenerator.generate();
	}
}