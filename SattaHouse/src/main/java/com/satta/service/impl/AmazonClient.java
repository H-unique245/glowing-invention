package com.satta.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AmazonClient {

	private AWSCredentials awsCredentials(String accesskey, String secretKey) {
		AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretKey);
		return credentials;
	}

	private AmazonS3 awsS3ClientBuilder(String accesskey, String secretKey) {
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials(accesskey, secretKey)))
				.withRegion(Regions.AP_SOUTH_1).build();
		return s3client;
	}

	public void uploadFIleToS3(String fileName, String accessKey, String secretKey, String bucket) {

	}

}
