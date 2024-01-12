package com.satta.response;

import com.satta.model.User;
import com.satta.payload.UserDTO;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;

	private UserDTO user;
}