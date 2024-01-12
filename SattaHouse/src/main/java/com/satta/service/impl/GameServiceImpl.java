package com.satta.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satta.constants.Status;
import com.satta.exception.ResourceNotFoundException;
import com.satta.model.Game;
import com.satta.model.Result;
import com.satta.repo.GameRepo;
import com.satta.repo.ResultRepo;
import com.satta.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepo gameRepo;

	@Autowired
	private ResultRepo resultRepo;

	@Override
	public List<Game> createGame(List<Game> games, String id) {
		Result result = this.resultRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No result with id: " + id, false));

		// Generate UUID for each game and set timestamps
		for (Game game : games) {
			String uuid = UUID.randomUUID().toString();
			game.setId(uuid);
			game.setCreatedAt(LocalDateTime.now());
			game.setModifiedAt(LocalDateTime.now());
		}

		// Save the list of games
		List<Game> savedGames = this.gameRepo.saveAll(games);

		// Update the Result entity with the saved games
		result.getGames().addAll(savedGames);
		this.resultRepo.save(result);

		return savedGames;
	}

	private static Status status;

	@Override
	public Game updateGame(Game game, String gameId) {
		Game existingGame = this.gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + gameId, false));

		// Use proper enum constant for comparison
		if (game.getStatus() == Status.PENDING) {
			existingGame.setStatus(Status.PENDING);
		} else if (game.getStatus() == Status.WAIT) {
			existingGame.setStatus(Status.WAIT);
			System.out.println("sdfsdfsdf");
			existingGame.setGameMessage("WAIT");
		} else {
			existingGame.setGameMessage("");
			existingGame.setGameResult(game.getGameResult());
			existingGame.setStatus(Status.DISPLAY);
		}

		existingGame.setModifiedAt(LocalDateTime.now());
		existingGame = this.gameRepo.save(existingGame);

		return existingGame;
	}

	@Override
	public Game getGame(String gameId) {
		return this.gameRepo.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("No game found with id: " + gameId, false));

	}

	@Override
	public String deleteGame(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
