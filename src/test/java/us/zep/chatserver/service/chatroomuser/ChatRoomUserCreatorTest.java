package us.zep.chatserver.service.chatroomuser;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroom.ChatRoomRepository;
import us.zep.chatserver.repository.chatroomuser.ChatRoomUserRepository;
import us.zep.chatserver.service.chatroom.ChatRoomCreator;

@ExtendWith(MockitoExtension.class)
public class ChatRoomUserCreatorTest {
	@Mock
	private ChatRoomUserRepository repository;
	private ChatRoomUserCreator sut;

	@BeforeEach
	void setUp() {
		sut = new ChatRoomUserCreator(repository);
	}

	@Test
	void 채팅방_유저를_맵핑하여_저장할_수_있다() {
		String userId = "김젭-UUID-UUID-UUID";
		String roomId = "단체방-UUID-UUID-UUID";

		sut.by(userId, roomId);

		verify(repository, times(1)).save(userId, roomId);
	}
}
