package us.zep.chatserver.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import us.zep.chatserver.common.exception.BaseException;
import us.zep.chatserver.entity.ChatRoom;
import us.zep.chatserver.repository.chatroom.ChatRoomRepository;

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
}
