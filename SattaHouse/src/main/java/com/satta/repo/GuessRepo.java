package com.satta.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satta.model.Guess;

@Repository
public interface GuessRepo extends JpaRepository<Guess, String> {
	List<Guess> findAllByOrderByCreatedAtDesc();
}
