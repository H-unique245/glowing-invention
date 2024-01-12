package com.satta.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.satta.config.AppConstants;
import com.satta.exception.ResourceAlreadyExists;
import com.satta.exception.ResourceNotFoundException;
import com.satta.model.Guess;
import com.satta.model.Role;
import com.satta.model.User;
import com.satta.repo.GuessRepo;
import com.satta.repo.RoleRepo;
import com.satta.repo.UserRepo;
import com.satta.response.JwtAuthRequest;
import com.satta.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private GuessRepo guessRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User signUp(User user) throws Exception {

		try {

			Role role = new Role();
			role.setId(AppConstants.NORMAL_USER);
			role.setName("ROLE_NORMAL");

			// Add only the "ROLE_NORMAL" role to the user's roles set
			user.getRoles().add(role);

			String password = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			String id = UUID.randomUUID().toString();
			user.setId(id);

			user = this.userRepo.save(user);
		} catch (DataIntegrityViolationException ex) {
			// Handle unique constraint violation
			throw new ResourceAlreadyExists("User already exists with this PhoneNumber: " + user.getPhoneNumber(),
					false);
		}

		return user;
	}

	@Override
	public User updateUser(User user, String id) {
		User savedUser = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id, false));
		savedUser.setActive(user.isActive());
		savedUser = this.userRepo.save(savedUser);
		return savedUser;
	}

	@Override
	public User getUser(String id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id, false));
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = this.userRepo.findAll();
		return allUsers;
	}

	@Override
	public User updatePassword(JwtAuthRequest request) {

		return null;
	}

	@Override
	public void deleteUser(String id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id, false));

		user.getRoles().clear();

		List<Guess> guess = user.getGuess();
		this.guessRepo.deleteAll(guess);
		this.userRepo.delete(user);

	}

}
