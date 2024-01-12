package com.satta.service;

import java.util.List;
import java.util.Optional;

import com.satta.model.Result;

public interface ResultService {

	Result createResult(Result result);

	Result getResult(String id);

	Result updateResult(String id, Result result);

	List<Result> allResults();

	List<Result> filterResult(int year, int month);

	Optional<Result> filteResult(int year, int month, int day);

	List<Result> filteResultSortedToGames(int year, int month, int day);

	List<List<Result>> findByYear(int year);

}
