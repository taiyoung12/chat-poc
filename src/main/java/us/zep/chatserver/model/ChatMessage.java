package us.zep.chatserver.model;

public class ChatMessage {
	private String content;
	private String sender;
	private String roomId;
	private MessageType type;


	public enum MessageType{
		CHAT,
		JOIN,
		LEAVE
	}

	public ChatMessage() {}

	public ChatMessage(
		String content,
	 	String sender,
		String roomId,
		MessageType type
	) {
		this.content = content;
		this.sender = sender;
		this.roomId = roomId;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public String getSender() {
		return sender;
	}

	public String getRoomId() {
		return roomId;
	}

	public MessageType getType() {
		return type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}


	public void setType(MessageType type) {
		this.type = type;
	}
}
