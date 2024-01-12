package com.satta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satta.model.Information;

@Repository
public interface InformationRepo extends JpaRepository<Information, String> {

}
