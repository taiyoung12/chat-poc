package us.zep.chatserver.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonCode implements Code {
	SUCCESS("CM200", "성공", HttpStatus.OK),

	INVALID_INPUT("CM400", "잘못된 입력입니다", HttpStatus.BAD_REQUEST),
	VALIDATION_ERROR("CM401", "유효성 검사에 실패했습니다", HttpStatus.BAD_REQUEST),
	UNAUTHORIZED("CM401", "인증이 필요합니다", HttpStatus.UNAUTHORIZED),

	INTERNAL_ERROR("CM500", "서버 내부 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
	RESOURCE_NOT_FOUND("CM404", "존재하지 않는 API END-POINT 입니다", HttpStatus.NOT_FOUND);


	private final String code;
	private final String message;
	private final HttpStatus httpStatus;
}
