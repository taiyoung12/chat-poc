package us.zep.chatserver.service.chatroom;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import us.zep.chatserver.controller.chatroom.response.ChatRoomReadReponse;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroom.ChatRoomRepository;

@ExtendWith(MockitoExtension.class)
public class ChatRoomReaderTest {
	@Mock
	private ChatRoomRepository repository;
	private ChatRoomReader sut;

	@BeforeEach
	void setUp() {
		sut = new ChatRoomReader(repository);
	}

	@Test
	void roomIds_로_채팅방의_이름을_조회할_수_있다() {
		List<String> roomIds = Arrays.asList("id1", "id2");
		ChatRoom room1 = new ChatRoom("room1");
		ChatRoom room2 = new ChatRoom("room2");

		ReflectionTestUtils.setField(room1, "id", "id1");
		ReflectionTestUtils.setField(room2, "id", "id2");

		when(repository.findBy(roomIds)).thenReturn(Arrays.asList(room1, room2));

		List<ChatRoomReadReponse> responses = sut.findBy(roomIds);

		assertThat(responses).hasSize(2);
		assertThat(responses)
			.extracting("roomId", "name")
			.containsExactlyInAnyOrder(
				tuple("id1", "room1"),
				tuple("id2", "room2")
			);
	}
}
