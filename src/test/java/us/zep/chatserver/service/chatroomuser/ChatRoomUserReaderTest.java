package us.zep.chatserver.service.chatroomuser;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import us.zep.chatserver.entity.UserChatRoom;
import us.zep.chatserver.repository.chatroomuser.ChatRoomUserRepository;

@ExtendWith(MockitoExtension.class)
public class ChatRoomUserReaderTest {
	@Mock
	private ChatRoomUserRepository repository;
	private ChatRoomUserReader sut;

	@BeforeEach
	void setUp() {
		sut = new ChatRoomUserReader(repository);
	}

	@Test
	void 채팅방_ID로_참여_중인_유저_ID_목록을_조회할_수_있다() {
		String roomId = "room-123";
		List<UserChatRoom> mockResult = List.of(
			new UserChatRoom("user1", roomId),
			new UserChatRoom("user2", roomId),
			new UserChatRoom("user3", roomId)
		);

		when(repository.findByChatRoomId(roomId)).thenReturn(mockResult);

		List<String> result = sut.findByRoomId(roomId);

		assertThat(result).hasSize(3)
			.containsExactlyInAnyOrder("user1", "user2", "user3");

		verify(repository, times(1)).findByChatRoomId(roomId);
	}

	@Test
	void 유저_ID로_참여_중인_방_ID_목록을_조회할_수_있다() {
		String userId = "user1";

		List<UserChatRoom> mockResult = List.of(
			new UserChatRoom(userId, "room1"),
			new UserChatRoom(userId, "room2"),
			new UserChatRoom(userId, "room3")
		);

		when(repository.findByUserId(userId)).thenReturn(mockResult);

		List<String> result = sut.findByUserId(userId);

		assertThat(result).hasSize(3)
			.containsExactlyInAnyOrder("room1", "room2", "room3");

		verify(repository, times(1)).findByUserId(userId);
	}
}
