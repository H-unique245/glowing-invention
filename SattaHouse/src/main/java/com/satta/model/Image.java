package com.satta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Image {

	@Id
	private String id;
	private String imgName;

	@Column(length = 2000)
	private String imgPath;
}
