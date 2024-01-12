package com.satta.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.satta.constants.Status;

import lombok.Data;

@Entity
@Data
public class Game {

	@Id
	private String id;

	@NotNull(message = "name can not be null")
	private String gameName;

	@NotNull(message = "Status can not be null")
	private Status status = Status.PENDING;

//	@NotNull(message = "gam can not be null")
	private String gameMessage;

	private String gameResult;

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

}
