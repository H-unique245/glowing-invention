package com.satta.service;

import java.util.List;

import com.satta.model.User;
import com.satta.response.JwtAuthRequest;

public interface UserService {

	User signUp(User user) throws Exception;

	User updateUser(User user, String id);

	User getUser(String id);

	List<User> getAllUsers();

	User updatePassword(JwtAuthRequest jwtAuthRequest);

	void deleteUser(String id);
}
