package us.zep.chatserver.common.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UUIDGeneratorTest {

	@Test
	void generate_메서드는_유효한_UUID를_반환해야_한다() {
		String actual = UUIDGenerator.generate();

		assertThat(actual).isNotNull();
		assertThat(actual).isNotEmpty();

		UUID parsed = UUID.fromString(actual);
		assertThat(parsed).isInstanceOf(UUID.class);
	}
}
