package us.zep.chatserver.dto;

import lombok.Data;

import java.time.LocalDateTime;

// TODO : 모든 코드는 수정하셔도 됩니다.
@Data
public class ChatRoomInfo {
    private String chatRoomId;
    private String chatRoomName;
    private LocalDateTime createdAt;

    public ChatRoomInfo(String chatRoomId, String chatRoomName, LocalDateTime createdAt) {
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.createdAt = createdAt;
    }
}