package us.zep.chatserver.entity;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChatRoomTest {
	@Test
	void chatRoom_을_생성할_수_있다(){
		ChatRoom actual = new ChatRoom();

		assertThat(actual).isNotNull();
	}

	@ParameterizedTest
	@ValueSource(strings = {"단체방", "1:1채팅"})
	void chatRoom_파라미터_생성자로_생성할_수_있다(String name){
		ChatRoom actual = new ChatRoom(name);

		assertThat(actual).isNotNull();
		assertThat(actual.getName()).isEqualTo(name);
	}
}
