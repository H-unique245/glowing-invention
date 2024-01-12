package com.satta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satta.model.Image;


@Repository
public interface ImageRepo extends JpaRepository<Image, String> {

	Image findByImgName(String imgName);
}
