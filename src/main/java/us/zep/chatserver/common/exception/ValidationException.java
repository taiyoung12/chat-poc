package us.zep.chatserver.common.exception;

import java.util.Map;

import lombok.Getter;
import us.zep.chatserver.common.code.CommonCode;

@Getter
public class ValidationException extends BaseException {
	private final Map<String, String> errors;

	public ValidationException(Map<String, String> errors) {
		super(CommonCode.VALIDATION_ERROR);
		this.errors = errors;
	}
}