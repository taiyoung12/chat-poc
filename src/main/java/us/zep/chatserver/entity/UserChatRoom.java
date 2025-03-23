package us.zep.chatserver.entity;

import java.time.LocalDateTime;

public class UserChatRoom {
	private String userId;
	private String chatRoomId;
	private LocalDateTime joinedAt;

	public UserChatRoom(String userId, String chatRoomId) {
		this.userId = userId;
		this.chatRoomId = chatRoomId;
		this.joinedAt = LocalDateTime.now();
	}

	public String getUserId() {
		return userId;
	}

	public String getChatRoomId() {
		return chatRoomId;
	}

	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}
}
