
package com.satta.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satta.model.Result;
import com.satta.response.ApiResponse;
import com.satta.service.ResultService;

@RestController
@RequestMapping("api/v1/result")
public class ResultController {

	@Autowired
	private ResultService resultService;

	@PostMapping
	public ResponseEntity<ApiResponse> saveResult(@Valid @RequestBody Result result) {
		Result createResult = this.resultService.createResult(result);
		ApiResponse response = new ApiResponse(true, createResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<ApiResponse> getResult(@PathVariable String id) {
		Result createResult = this.resultService.getResult(id);
		ApiResponse response = new ApiResponse(true, createResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getResults() {
		List<Result> allResults = this.resultService.allResults();

		ApiResponse response = new ApiResponse(true, allResults);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping("/filter")
	public ResponseEntity<ApiResponse> filterResult(@RequestParam int year, @RequestParam int month) {
		List<Result> filterResult = this.resultService.filterResult(year, month);
		ApiResponse response = new ApiResponse(true, filterResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/filter/particularDay")
	public ResponseEntity<ApiResponse> filterResultDay(@RequestParam int year, @RequestParam int month,
			@RequestParam int day) {
		Optional<Result> filterResult = this.resultService.filteResult(year, month, day);

		ApiResponse response = new ApiResponse(true, filterResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/filter/nameSortedDay")
	public ResponseEntity<ApiResponse> nameSorted(@RequestParam int year, @RequestParam int month,
			@RequestParam int day) {
		List<Result> filterResult = this.resultService.filteResultSortedToGames(year, month, day);

		ApiResponse response = new ApiResponse(true, filterResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/filter/byYear")
	public ResponseEntity<ApiResponse> nameSorted(@RequestParam int year) {
		List<List<Result>> filterResult = this.resultService.findByYear(year);

		ApiResponse response = new ApiResponse(true, filterResult);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

}
