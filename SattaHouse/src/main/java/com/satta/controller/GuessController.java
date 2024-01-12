package com.satta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satta.model.Guess;
import com.satta.response.ApiResponse;
import com.satta.service.GuessService;

@RestController
@RequestMapping("api/v1/guess")
public class GuessController {

	@Autowired
	private GuessService guessService;

	@PostMapping("{userId}")
	public ResponseEntity<ApiResponse> createGuss(@RequestBody Guess guess, @PathVariable String userId) {
		Guess createGuess = this.guessService.createGuess(guess, userId);
		ApiResponse response = new ApiResponse();
		response.setData(createGuess);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping("{guessId}")
	public ResponseEntity<ApiResponse> createGuss(@PathVariable String guessId) {
		Guess guess = this.guessService.getGuess(guessId);
		ApiResponse response = new ApiResponse();
		response.setData(guess);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> createGuss() {
		List<Guess> guess = this.guessService.allGuess();
		ApiResponse response = new ApiResponse();
		response.setData(guess);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
