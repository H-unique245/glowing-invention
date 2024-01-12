package com.satta.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Information {

	@Id
	private String id;

	private String socialMediaLink1;
	private String socialMediaLink2;
	private String socialMediaLink3;
	private String socialMediaLink4;
	private String socialMediaLink5;
	private String socialMediaLink6;

	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;
	private String phoneNumber4;
	private String phoneNumber5;
	private String phoneNumber6;

	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String title5;
	private String title6;
	private String title7;
	private String title8;

	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String img6;
	private String img7;
	private String img8;

}
