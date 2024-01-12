package com.satta.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.satta.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ApiResponse> apiResponseHandler(ApiResponse apiResponse) {
		ApiResponse api = new ApiResponse();
		api.setMessage(false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMsg();
		ApiResponse apiResponse = new ApiResponse(false, message);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<ApiResponse> ResourceAlreadyExistsExceptionHandler(ResourceAlreadyExists ex) {
		String message = ex.getMsg();
		ApiResponse apiResponse = new ApiResponse(false, message);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		// Define custom error message and error code
		String errorMessage = "Validation failed.";
		int errorCode = 400;

		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("errorMessage", errorMessage);
		errorResponse.put("errorCode", errorCode);
		errorResponse.put("errors", errors);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
