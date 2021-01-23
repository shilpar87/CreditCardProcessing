package org.test.proj.ccprocessor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.test.proj.ccprocessor.model.CreditCardInfoData;
import org.test.proj.ccprocessor.model.Status;
import org.test.proj.ccprocessor.service.CCProcessorService;

@RestController
public class CCProcessorController {

	@Autowired
	CCProcessorService ccProcessService;

	@PostMapping("/creditcards/add")
	private Status addCreditCard(@RequestBody CreditCardInfoData data) {
		return ccProcessService.addCreditCard(data);
	}
	
	@GetMapping("/creditcards/getall")
	private List<CreditCardInfoData> getAllCreditCards() {
		return ccProcessService.getAllCreditCards();
	}
}