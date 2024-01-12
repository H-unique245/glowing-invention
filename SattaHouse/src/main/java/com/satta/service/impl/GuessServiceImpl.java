package com.satta.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satta.exception.ResourceNotFoundException;
import com.satta.model.Guess;
import com.satta.model.User;
import com.satta.repo.GuessRepo;
import com.satta.repo.UserRepo;
import com.satta.service.GuessService;

@Service
public class GuessServiceImpl implements GuessService {

	@Autowired
	private GuessRepo guessRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Guess createGuess(Guess guess, String userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No user found with id", false));

		String id = UUID.randomUUID().toString();
		guess.setId(id);
		guess.setName(user.getName());
		guess.setCreatedAt(LocalDateTime.now());
		guess.setModifiedAt(LocalDateTime.now());
		guess = this.guessRepo.save(guess);
		user.getGuess().add(guess);
		this.userRepo.save(user);

		return guess;
	}

	@Override
	public Guess getGuess(String id) {

		Guess guess = this.guessRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No resource found with this id: " + id, true));

		return guess;
	}

	@Override
	public void delteGuess(String id) {
		Guess guess = this.guessRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No resource found with this id: " + id, true));
		this.guessRepo.delete(guess);

	}

	@Override
	public List<Guess> allGuess() {
		List<Guess> findAll = this.guessRepo.findAllByOrderByCreatedAtDesc();

		return findAll;
	}

}
