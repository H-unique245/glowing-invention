package com.satta.service;

import java.util.List;

import com.satta.model.Guess;

public interface GuessService {

	Guess createGuess(Guess guess, String id);

	Guess getGuess(String id);

	List<Guess> allGuess();

	void delteGuess(String id);
}
