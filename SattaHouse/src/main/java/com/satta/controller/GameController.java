package com.satta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satta.model.Game;
import com.satta.repo.GameRepo;
import com.satta.response.ApiResponse;
import com.satta.service.GameService;

@RestController
@RequestMapping("api/v1/game")
public class GameController {

	@Autowired
	GameService gameService;

	@Autowired
	GameRepo gameRepo;

	@PostMapping("{id}")
	public ResponseEntity<ApiResponse> createGame(@Valid @RequestBody List<Game> game, @PathVariable String id) {
		List<Game> createGame = this.gameService.createGame(game, id);
		ApiResponse response = new ApiResponse();
		response.setData(createGame);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<ApiResponse> updateGame(@PathVariable String id, @RequestBody Game game) {
		Game updateGame = this.gameService.updateGame(game, id);
		ApiResponse response = new ApiResponse();
		response.setData(updateGame);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/bulkUpload")
	public ResponseEntity<List<Game>> createGames(@RequestBody List<Game> games) {
		List<Game> savedGames = new ArrayList<>();

		for (Game game : games) {
			String id = UUID.randomUUID().toString();
			game.setId(id);
			savedGames.add(this.gameRepo.save(game));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(savedGames);
	}

}
