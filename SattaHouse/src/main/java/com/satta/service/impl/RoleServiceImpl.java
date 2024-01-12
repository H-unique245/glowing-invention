package com.satta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satta.repo.RoleRepo;

@Service
public class RoleServiceImpl {

	@Autowired
	private RoleRepo roleRepo;
	
}
