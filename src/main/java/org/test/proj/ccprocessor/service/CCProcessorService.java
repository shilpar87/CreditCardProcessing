package org.test.proj.ccprocessor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.test.proj.ccprocessor.model.CreditCardInfoData;
import org.test.proj.ccprocessor.model.Status;
import org.test.proj.ccprocessor.repository.CCProcessorRepository;
import org.test.proj.ccprocessor.validators.Validator;

@Service
public class CCProcessorService {

	@Autowired
	CCProcessorRepository ccProcesssorRepository;

	public Status addCreditCard(CreditCardInfoData data) {
		Status status = new Status();

		// Validations

		if (data == null) {
			status.setMessage("No valid data given");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}
		if (data.getCardNumber() == null || data.getCardNumber().isEmpty()) {
			status.setMessage("No valid Card Number given");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		} else if (data.getCardLimit() == null || data.getCardLimit().isEmpty()) {
			status.setMessage("No valid Limit given");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		} else if (data.getName() == null || data.getName().isEmpty()) {
			status.setMessage("No valid Name given");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}
		
		if (data.getCardNumber().length() > 19) {
			status.setMessage("Not a valid Card Number");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}

		if (!Validator.onlyDigits(data.getCardNumber())) {
			status.setMessage("Not a valid Card Number");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}

		if (!Validator.onlyDigits(data.getCardLimit())) {
			status.setMessage("Not a valid Limit");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}
		
		if (!Validator.checkValidCardNumber(data.getCardNumber())) {
			status.setMessage("Not a valid Card Number");
			status.setStatusCode(HttpStatus.BAD_REQUEST.toString());
			return status;
		}
		
		//set balance to 0 for cards
		
		data.setCardBalance("0");

		// save to database
		ccProcesssorRepository.save(data);

		status.setMessage("Card Details saved successfully");
		status.setStatusCode(HttpStatus.ACCEPTED.toString());

		return status;

	}

	public List<CreditCardInfoData> getAllCreditCards() {
		return (List<CreditCardInfoData>)ccProcesssorRepository.findAll();
	}
}