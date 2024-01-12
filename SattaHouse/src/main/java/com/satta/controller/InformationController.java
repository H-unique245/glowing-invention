package com.satta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satta.model.Information;
import com.satta.response.ApiResponse;
import com.satta.service.InformationService;

@RestController
@RequestMapping("api/v1/information")
public class InformationController {

	@Autowired
	private InformationService informationServie;

	@PostMapping
	public ResponseEntity<ApiResponse> createInformation(@RequestBody Information information) {
		Information createInformation = this.informationServie.createInformation(information);
		ApiResponse response = new ApiResponse();
		response.setData(createInformation);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<ApiResponse> updateInformation(@RequestBody Information information,
			@PathVariable String id) {
		Information updateInformation = this.informationServie.updateInformation(id, information);
		ApiResponse response = new ApiResponse();
		response.setData(updateInformation);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse> getIformation(@PathVariable String id) {
		Information updateInformation = this.informationServie.getInformation(id);
		ApiResponse response = new ApiResponse();
		response.setData(updateInformation);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getIformation() {
		List<Information> updateInformation = this.informationServie.allInformation();
		ApiResponse response = new ApiResponse();
		response.setData(updateInformation);
		response.setMessage(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
