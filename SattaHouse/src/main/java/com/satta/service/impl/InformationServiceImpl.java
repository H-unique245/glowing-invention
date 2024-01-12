package com.satta.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satta.exception.ResourceNotFoundException;
import com.satta.model.Information;
import com.satta.repo.InformationRepo;
import com.satta.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationRepo informationRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Information createInformation(Information information) {

		String id = UUID.randomUUID().toString();
		information.setId(id);
		information = this.informationRepo.save(information);

		return information;
	}

	@Override
	public Information getInformation(String id) {
		Information information = this.informationRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Information found by id: " + id, false));

		return information;
	}

	@Override
	public Information updateInformation(String id, Information updateInformation) {
		Information information = this.informationRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Information found by id: " + id, false));

		// Update social media links
		updateFieldIfNotNull(updateInformation.getSocialMediaLink1(), information::setSocialMediaLink1);
		updateFieldIfNotNull(updateInformation.getSocialMediaLink2(), information::setSocialMediaLink2);
		updateFieldIfNotNull(updateInformation.getSocialMediaLink3(), information::setSocialMediaLink3);
		updateFieldIfNotNull(updateInformation.getSocialMediaLink4(), information::setSocialMediaLink4);
		updateFieldIfNotNull(updateInformation.getSocialMediaLink5(), information::setSocialMediaLink5);
		updateFieldIfNotNull(updateInformation.getSocialMediaLink6(), information::setSocialMediaLink6);

		// Update phone numbers
		updateFieldIfNotNull(updateInformation.getPhoneNumber1(), information::setPhoneNumber1);
		updateFieldIfNotNull(updateInformation.getPhoneNumber2(), information::setPhoneNumber2);
		updateFieldIfNotNull(updateInformation.getPhoneNumber3(), information::setPhoneNumber3);
		updateFieldIfNotNull(updateInformation.getPhoneNumber4(), information::setPhoneNumber4);
		updateFieldIfNotNull(updateInformation.getPhoneNumber5(), information::setPhoneNumber5);
		updateFieldIfNotNull(updateInformation.getPhoneNumber6(), information::setPhoneNumber6);

		// Update titles
		updateFieldIfNotNull(updateInformation.getTitle1(), information::setTitle1);
		updateFieldIfNotNull(updateInformation.getTitle2(), information::setTitle2);
		updateFieldIfNotNull(updateInformation.getTitle3(), information::setTitle3);
		updateFieldIfNotNull(updateInformation.getTitle4(), information::setTitle4);
		updateFieldIfNotNull(updateInformation.getTitle5(), information::setTitle5);
		updateFieldIfNotNull(updateInformation.getTitle6(), information::setTitle6);
		updateFieldIfNotNull(updateInformation.getTitle7(), information::setTitle7);
		updateFieldIfNotNull(updateInformation.getTitle8(), information::setTitle8);

		// Update images
		updateFieldIfNotNull(updateInformation.getImg1(), information::setImg1);
		updateFieldIfNotNull(updateInformation.getImg2(), information::setImg2);
		updateFieldIfNotNull(updateInformation.getImg3(), information::setImg3);
		updateFieldIfNotNull(updateInformation.getImg4(), information::setImg4);
		updateFieldIfNotNull(updateInformation.getImg5(), information::setImg5);
		updateFieldIfNotNull(updateInformation.getImg6(), information::setImg6);
		updateFieldIfNotNull(updateInformation.getImg7(), information::setImg7);
		updateFieldIfNotNull(updateInformation.getImg8(), information::setImg8);

		// Save the updated information
		return this.informationRepo.save(information);
	}

	// Helper method to update a field if the new value is not null
	private <T> void updateFieldIfNotNull(T newValue, Consumer<T> setter) {
		if (newValue != null) {
			setter.accept(newValue);
		}
	}

	@Override
	public List<Information> allInformation() {
		List<Information> list = this.informationRepo.findAll();
		return list;
	}

}
