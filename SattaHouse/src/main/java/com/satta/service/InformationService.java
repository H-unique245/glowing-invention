package com.satta.service;

import java.util.List;

import com.satta.model.Information;

public interface InformationService {

	Information createInformation(Information information);

	Information getInformation(String id);

	Information updateInformation(String id, Information information);

	List<Information> allInformation();

}
