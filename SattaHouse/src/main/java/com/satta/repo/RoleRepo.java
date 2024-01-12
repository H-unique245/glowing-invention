package com.satta.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satta.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	List<Role> findById(int id);

}
