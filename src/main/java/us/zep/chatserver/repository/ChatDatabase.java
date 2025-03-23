package us.zep.chatserver.repository;

import org.springframework.stereotype.Repository;
import us.zep.chatserver.dto.ChatRoomInfo;

import java.util.ArrayList;
import java.util.List;


// TODO : 모든 코드는 수정하셔도 됩니다.
@Repository
public class ChatDatabase {
    private final List<ChatRoomInfo> chatRoomInfoList = new ArrayList<>();

    public List<ChatRoomInfo> findAllChatRooms() {
        return chatRoomInfoList;
    }

    public void insert(ChatRoomInfo chatRoomInfo) {
        chatRoomInfoList.add(chatRoomInfo);
    }

    public void deleteByRoomId(String roomId) {
        chatRoomInfoList.removeIf(chatRoomInfo -> chatRoomInfo.getChatRoomId().equals(roomId));
    }
}
