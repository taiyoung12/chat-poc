package us.zep.chatserver.dto;

import lombok.*;
import us.zep.chatserver.model.ChatMessageType;

// TODO : 모든 코드는 수정하셔도 됩니다.
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String chatRoomId;
    private ChatMessageType messageType;
    private Long senderId;
    private String senderName;
    private String message;
    private String time;
}
