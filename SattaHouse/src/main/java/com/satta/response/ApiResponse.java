package com.satta.response;

import lombok.Data;

@Data
public class ApiResponse {

	private Boolean message;
	private Object data;

	public ApiResponse(Boolean message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}

	public ApiResponse() {

	}

}
