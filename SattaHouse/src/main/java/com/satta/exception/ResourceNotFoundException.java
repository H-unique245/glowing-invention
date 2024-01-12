package com.satta.exception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String msg, Boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}

	private String msg;
	private Boolean status;

	public ResourceNotFoundException() {
		super();
	}

}
