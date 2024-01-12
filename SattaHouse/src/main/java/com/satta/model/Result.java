package com.satta.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class Result {

	@Id
	private String id;

	@Min(value = 1, message = "Game month should be minimum 1")
	@Max(value = 12, message = "Game month should be maximum 12")
	private int gameMonth;
	@Min(value = 2020, message = "Game year should be minimum 2020")
	@Max(value = 3000, message = "Game year should be maximum 3000")
	private int gameYear;

	@Min(value = 1, message = "Game month should be minimum 1")
	@Max(value = 31, message = "Game month should be maximum 31")
	private int date;

	private LocalDateTime createdAt;

	@OrderBy("modifiedAt DESC")
	private LocalDateTime modifiedAt;

	@OrderBy("modifiedAt DESC") // Sort by modifiedAt in descending order
	@OneToMany
	private List<Game> games;
}
