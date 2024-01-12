package com.satta.exception;

import lombok.Data;

@Data
public class ResourceAlreadyExists extends RuntimeException {

	public ResourceAlreadyExists(String msg, Boolean status) {
		super();
		this.msg = msg;
		this.status = status;
	}

	private String msg;
	private Boolean status;

	public ResourceAlreadyExists() {
		super();
	}

}
