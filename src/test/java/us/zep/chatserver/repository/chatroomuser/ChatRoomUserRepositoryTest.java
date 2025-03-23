package us.zep.chatserver.repository.chatroomuser;


import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import us.zep.chatserver.entity.UserChatRoom;

public class ChatRoomUserRepositoryTest {
	private final ChatRoomUserRepository sut = new ChatRoomUserRepository();

	@Test
	void save_유저와_채팅방_관계를_저장한다() {
		String userId = "user1";
		String chatRoomId = "room1";

		sut.save(userId, chatRoomId);

		Optional<UserChatRoom> result = sut.findByUserIdAndChatRoomId(userId, chatRoomId);
		assertThat(result).isPresent();
	}

	@Test
	void find_존재하지_않는_관계는_없다() {
		String userId = "nonExistentUser";
		String chatRoomId = "nonExistentRoom";

		Optional<UserChatRoom> result = sut.findByUserIdAndChatRoomId(userId, chatRoomId);

		assertThat(result).isEmpty();
	}

}
