package com.satta.requests;

import com.satta.constants.Status;

import lombok.Data;

@Data
public class GameRequest {

	private Status status;
	private String gameName;
	private String gameResult;

}
