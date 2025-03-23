package us.zep.chatserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.zep.chatserver.dto.ChatRoomInfo;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    // TODO: 채팅방 생성
    @PostMapping("/rooms")
    public String createRoom(@RequestParam String name) {
        return null;
    }

    // TODO: 현재 존재하는 채팅방 목록 조회
    @GetMapping("/rooms")
    public List<ChatRoomInfo> getRooms() {
        return null;
    }
}
