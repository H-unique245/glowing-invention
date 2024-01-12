package com.satta.payload;

import java.util.HashSet;
import java.util.Set;

import com.satta.model.Role;

import lombok.Data;

@Data
public class UserDTO {

	private String id;
	private String name;
	private String gender;
	private String state;
	private String email;
	private String phoneNumber;
	private String password;
	private boolean active;
	
	private Set<Role> roles = new HashSet<>();
}
