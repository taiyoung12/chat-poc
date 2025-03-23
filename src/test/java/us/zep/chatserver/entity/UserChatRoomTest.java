package us.zep.chatserver.entity;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UserChatRoomTest {

	@Test
	void userChatRoom_생성_테스트() {
		String userId = "김젭-UUID-UUID-UUID";
		String chatRoomId = "단체방-UUID-UUID-UUID";

		UserChatRoom userChatRoom = new UserChatRoom(userId, chatRoomId);

		assertThat(userChatRoom).isNotNull();
		assertThat(userChatRoom.getUserId()).isEqualTo(userId);
		assertThat(userChatRoom.getChatRoomId()).isEqualTo(chatRoomId);
		assertThat(userChatRoom.getJoinedAt()).isNotNull();
		assertThat(userChatRoom.getJoinedAt()).isBeforeOrEqualTo(LocalDateTime.now());
	}
}
