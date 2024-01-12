package com.satta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satta.model.User;
import com.satta.response.ApiResponse;
import com.satta.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse> getUser(@PathVariable String id) {
		User user = this.userService.getUser(id);
		ApiResponse response = new ApiResponse(true, user);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAllUsers() {
		List<User> allUsers = this.userService.getAllUsers();
		ApiResponse response = new ApiResponse();
		response.setData(allUsers);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody User user, @PathVariable String id) {
		User updateUser = this.userService.updateUser(user, id);
		ApiResponse response = new ApiResponse(true, updateUser);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		this.userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
