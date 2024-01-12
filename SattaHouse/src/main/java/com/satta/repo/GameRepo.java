package com.satta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satta.model.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, String> {

}
