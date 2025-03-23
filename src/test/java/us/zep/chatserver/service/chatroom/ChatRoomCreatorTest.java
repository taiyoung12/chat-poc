package us.zep.chatserver.service.chatroom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import us.zep.chatserver.common.exception.BaseException;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.ChatRoomRepository;

@ExtendWith(MockitoExtension.class)
public class ChatRoomCreatorTest {
	@Mock
	private ChatRoomRepository repository;
	private ChatRoomCreator sut;

	@BeforeEach
	void setUp() {
		sut = new ChatRoomCreator(repository);
	}

	@Test
	void 채팅방_생성_요청_응답_DTO를_반환할_수_있다() {
		String name = "단체방";
		ChatRoom chatRoom = new ChatRoom(name);

		when(repository.save(name)).thenReturn(chatRoom);

		sut.by(name);

		verify(repository, times(1)).save(name);
	}
}
