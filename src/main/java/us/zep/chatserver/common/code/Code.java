package us.zep.chatserver.common.code;

import org.springframework.http.HttpStatus;

public interface Code {
	String getCode();

	String getMessage();

	HttpStatus getHttpStatus();
}
