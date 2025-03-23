package us.zep.chatserver.repository.chatroomuser;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
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


	@Test
	void 채팅방에_참여_중인_모든_유저를_조회할_수_있다() {
		String chatRoomId = "room-1";
		sut.save("user1", chatRoomId);
		sut.save("user2", chatRoomId);
		sut.save("user3", "room-2");

		List<UserChatRoom> result = sut.findByChatRoomId(chatRoomId);

		assertThat(result)
			.hasSize(2)
			.extracting(UserChatRoom::getUserId)
			.containsExactlyInAnyOrder("user1", "user2");
	}

	@Test
	void 유저가_참여_중인_모든_채팅방을_조회할_수_있다() {
		String userId = "user1";
		sut.save(userId, "room-1");
		sut.save(userId, "room-2");
		sut.save("user2", "room-1");

		List<UserChatRoom> result = sut.findByUserId(userId);

		assertThat(result)
			.hasSize(2)
			.extracting(UserChatRoom::getChatRoomId)
			.containsExactlyInAnyOrder("room-1", "room-2");
	}
}
