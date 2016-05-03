package org.jrue.ems.department.dto;

import org.springframework.http.HttpStatus;

public class RestErrorDTO {

	private final long timeStamp;
	private final HttpStatus status;
	private final String error;
	private final String message;

	public RestErrorDTO(long timeStamp, HttpStatus status, String error, String message) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}


	public String getMessage() {
		return message;
	}

}
