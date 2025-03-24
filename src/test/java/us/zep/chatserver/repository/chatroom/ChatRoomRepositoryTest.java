package us.zep.chatserver.repository.chatroom;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import us.zep.chatserver.common.exception.BaseException;
import us.zep.chatserver.entity.ChatRoom;

public class ChatRoomRepositoryTest {
	private final ChatRoomRepository sut = new ChatRoomRepository();

	@Test
	void 채팅방을_생성할_수_있다(){
		String name = "단체방";
		ChatRoom actual = sut.save(name);

		assertThat(actual.getName()).isEqualTo(name);
	}

	@Test
	void 채팅방_이름은_null_일_수_없다(){
		String emptyString = "";

		assertThrows(BaseException.class, () -> {
			sut.save(emptyString);
		});
	}

	@Test
	void 채팅방_이름은_공백_일_수_없다(){
		String blankString = "     ";

		assertThrows(BaseException.class, () -> {
			sut.save(blankString);
		});
	}

	@Test
	void 채팅방을_조회할_수_있다() {
		ChatRoom room1 = sut.save("방1");
		ChatRoom room2 = sut.save("방2");
		ChatRoom room3 = sut.save("방3");

		List<String> searchIds = List.of(room1.getId(), room2.getId());
		List<ChatRoom> foundRooms = sut.findBy(searchIds);

		assertThat(foundRooms)
			.hasSize(2)
			.extracting(ChatRoom::getId)
			.containsExactlyInAnyOrder(room1.getId(), room2.getId());
	}

	@Test
	void List가_비어있을_때_empty를_반화할_수_있다() {
		List<ChatRoom> foundRooms = sut.findBy(List.of());
		assertThat(foundRooms).isEmpty();
	}
}
