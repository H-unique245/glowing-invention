package com.satta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.satta.model.Image;
import com.satta.response.ApiResponse;
import com.satta.service.impl.FileUploadUtil;

@RestController
@RequestMapping("api/v1/images")
public class ImageController {

	@Autowired
	private FileUploadUtil amazonClient;

	@PostMapping
	public ResponseEntity<ApiResponse> uploadFile(@RequestPart(value = "file") MultipartFile file) {
		Image uploadFile = this.amazonClient.uploadFile(file);
		ApiResponse response = new ApiResponse();
		response.setMessage(true);
		response.setData(uploadFile);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFile/{fileUrl}")
	public String deleteFile(@PathVariable String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

}
