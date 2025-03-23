package us.zep.chatserver.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatRoomCode implements Code{
	INVALID_ROOM_NAME_INPUT("CR400", "채팅방의 이름은 비어있을 수 없습니다", HttpStatus.BAD_REQUEST),
	SUCCESS_ADD_USER_TO_ROOM("RU200", "유저가 채팅방에 참가하는것에 성공하였습니다", HttpStatus.OK);

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;
}
