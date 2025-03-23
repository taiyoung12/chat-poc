package us.zep.chatserver.common.exception;

import lombok.Getter;
import us.zep.chatserver.common.code.Code;

@Getter
public class BaseException extends RuntimeException {
	private final Code code;

	public BaseException(Code code) {
		super(code.getMessage());
		this.code = code;
	}

	public BaseException(Code code, String message) {
		super(message);
		this.code = code;
	}
}