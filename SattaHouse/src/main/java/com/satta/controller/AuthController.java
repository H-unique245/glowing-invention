package com.satta.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satta.exception.ResourceNotFoundException;
import com.satta.model.User;
import com.satta.payload.UserDTO;
import com.satta.repo.UserRepo;
import com.satta.response.ApiResponse;
import com.satta.response.JwtAuthRequest;
import com.satta.response.JwtAuthResponse;
import com.satta.security.JwtTokenHelper;
import com.satta.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PutMapping("/{username}/{password}")
	public ResponseEntity<ApiResponse> updatePassword(@RequestBody JwtAuthRequest request,
			@PathVariable String username, @PathVariable String password) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());

		Optional<User> user = this.userRepo.findByPhoneNumber(request.getUsername());

		user.get().setPhoneNumber(username);

		String encodedPassword = this.passwordEncoder.encode(password);
		user.get().setPassword(encodedPassword);
		User save = this.userRepo.save(user.get());
		ApiResponse response = new ApiResponse();
		response.setMessage(true);
		response.setData(save);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		System.out.println(userDetails);
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		response.setUser(this.mapper.map(userDetails, UserDTO.class));
		ApiResponse result = new ApiResponse(true, response);

		return new ResponseEntity<ApiResponse>(result, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new Exception("Invalid username or password !!");
		}

	}

	@PostMapping("/signUp")
	public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody User user) throws Exception {
		User signUp = this.userService.signUp(user);

		ApiResponse api = new ApiResponse(true, signUp);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.CREATED);
	}

	@PostMapping("/updatePassword/{phoneNumber}/{password}")
	public ResponseEntity<JwtAuthResponse> updatePassword(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		if (userDetails != null) {

			User user = this.userRepo.findByPhoneNumber(userDetails.getUsername()).orElseThrow(
					() -> new ResourceNotFoundException("No user found with username :" + userDetails.getUsername(),
							false));

			user.setPassword(null);

		}

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		response.setUser(this.mapper.map(userDetails, UserDTO.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

}
