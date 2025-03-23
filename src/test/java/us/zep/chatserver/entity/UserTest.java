package us.zep.chatserver.entity;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserTest {

	@Test
	void user_를_생성할_수_있다() {
		User actual = new User();

		assertThat(actual).isNotNull();
	}

	@ParameterizedTest
	@CsvSource({
		"김젭, kimzep@example.com",
		"박젭, parkzep@example.com"
	})
	void user_파라미터_생성자로_생성할_수_있다(String name, String email) {
		User user = new User(name, email);

		assertThat(user).isNotNull();
		assertThat(user.getName()).isEqualTo(name);
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
