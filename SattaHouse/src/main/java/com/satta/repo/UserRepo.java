package com.satta.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satta.model.User;

public interface UserRepo extends JpaRepository<User, String> {

	Optional<User> findByPhoneNumber(String phoneNumber);

}