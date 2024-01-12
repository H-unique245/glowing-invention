package com.satta.service;

import java.util.List;

import com.satta.model.Game;

public interface GameService {

	List<Game> createGame(List<Game> game, String id);

	Game updateGame(Game game, String gameId);

	Game getGame(String gameId);

	String deleteGame(String id);

}
