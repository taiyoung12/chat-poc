package us.zep.chatserver.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RedisCode implements Code {
	PUBLISH_MESSAGE_SERIALIZATION_ERROR("RP500", "메시지 PUBLISH 단계에서 직렬화에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
	SUBSCRIBE_MESSAGE_SERIALIZATION_ERROR("RS500", "메시지 SUBSCRIBE 단계에서 직렬화에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR)
	;
	private final String code;
	private final String message;
	private final HttpStatus httpStatus;
}
