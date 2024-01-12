package com.satta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satta.exception.ResourceAlreadyExists;
import com.satta.exception.ResourceNotFoundException;
import com.satta.model.Result;
import com.satta.repo.ResultRepo;
import com.satta.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultRepo resultRepo;

	@Override
	public Result createResult(Result result) {
		Optional<Result> existingResult = this.resultRepo.findByGameYearAndGameMonthAndDate(result.getGameYear(),
				result.getGameMonth(), result.getDate());

		if (existingResult.isPresent()) {
			throw new ResourceAlreadyExists("Game already exists with this date: ", false);
		} else {
			String id = UUID.randomUUID().toString();
			result.setId(id);
			result.setCreatedAt(LocalDateTime.now());
			result.setModifiedAt(LocalDateTime.now());
			Result savedResult = this.resultRepo.save(result);

			return savedResult;
		}
	}

	@Override
	public Result getResult(String id) {
		Result result = this.resultRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No result with id: " + id, false));

		return result;
	}

	@Override
	public Result updateResult(String id, Result request) {
		Result result = this.resultRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No result with id: " + id, false));

		if (request.getDate() > 0) {

			result.setDate(request.getDate());
		} else if (request.getGameMonth() > 0) {
			result.setGameMonth(request.getGameMonth());
		} else if (request.getGameYear() > 0) {
			result.setGameYear(request.getGameYear());
		}

		result.setModifiedAt(LocalDateTime.now());
		result = this.resultRepo.save(result);

		return result;
	}

	@Override
	public List<Result> filterResult(int year, int month) {

		List<Result> gameMonth = this.resultRepo.findByGameYearAndGameMonthOrderByDateAsc(year, month);

		return gameMonth;
	}

	@Override
	public List<Result> allResults() {

		List<Result> results = this.resultRepo.findAll();

		return results;
	}

	@Override
	public Optional<Result> filteResult(int year, int month, int day) {

		Optional<Result> findByGameYearAndGameMonthAndDate = this.resultRepo.findByGameYearAndGameMonthAndDate(year,
				month, day);

		return findByGameYearAndGameMonthAndDate;
	}

	@Override
	public List<Result> filteResultSortedToGames(int year, int month, int day) {
		return null;

	}

	@Override
	public List<List<Result>> findByYear(int year) {
		List<List<Result>> resultList = new ArrayList<>();

		for (int i = 1; i < 13; i++) {
			List<Result> result = this.resultRepo.findByGameYearAndGameMonthOrderByDateAsc(year, i);
			resultList.add(result);
		}

		return resultList;
	}

}
