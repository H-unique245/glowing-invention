package com.satta.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satta.model.Result;

public interface ResultRepo extends JpaRepository<Result, String> {

	List<Result> findByGameYearAndGameMonthOrderByDateAsc(int gameYear, int gameMonth);

	List<Result> findByGameYearOrderByGameMonthAsc (int gameYear);

	Optional<Result> findByGameYearAndGameMonthAndDate(int gameYear, int gameMonth, int date);

}
