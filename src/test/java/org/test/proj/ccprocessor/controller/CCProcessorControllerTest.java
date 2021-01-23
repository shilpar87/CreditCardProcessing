package org.test.proj.ccprocessor.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.proj.ccprocessor.CCProcessorApp;
import org.test.proj.ccprocessor.model.CreditCardInfoData;
import org.test.proj.ccprocessor.model.Status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CCProcessorApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CCProcessorControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testAddCreditCard() {
		CreditCardInfoData input = new CreditCardInfoData();
			
		input.setName("Tester Test1");
		input.setCardNumber("1111222233334444");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("202 ACCEPTED", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("Card Details saved successfully", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardMissingName() {
		CreditCardInfoData input = new CreditCardInfoData();
			
		input.setCardNumber("1111222233334444");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("No valid Name given", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardMissingCardNumber() {
		CreditCardInfoData input = new CreditCardInfoData();
			
		input.setName("Tester Test1");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("No valid Card Number given", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardMissingLimit() {
		CreditCardInfoData input = new CreditCardInfoData();
			
		input.setName("Tester Test1");
		input.setCardNumber("1111222233334444");
	
		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("No valid Limit given", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardMissingParams() {
		CreditCardInfoData input = new CreditCardInfoData();

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("No valid Card Number given", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardDataNull() {
		CreditCardInfoData input = null;

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
		
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is4xxClientError());
	}
	
	@Test
	public void testAddCreditCardWrongCardNumber() {
		CreditCardInfoData input = new CreditCardInfoData();
		
		input.setName("Tester Test1");
		input.setCardNumber("1235123443215678");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("Not a valid Card Number", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardWrongCardNumberLengthLonger() {
		CreditCardInfoData input = new CreditCardInfoData();
		
		input.setName("Tester Test1");
		input.setCardNumber("1235123443215678123456");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("Not a valid Card Number", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardWrongCardNumberAlphanumeric() {
		CreditCardInfoData input = new CreditCardInfoData();
		
		input.setName("Tester Test1");
		input.setCardNumber("ABCD222233334444");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("Not a valid Card Number", ((Status) postResponse.getBody()).getMessage());
	}
	
	@Test
	public void testAddCreditCardWrongLimitAlphanumeric() {
		CreditCardInfoData input = new CreditCardInfoData();
		
		input.setName("Tester Test1");
		input.setCardNumber("1111222233334444");
		input.setCardLimit("A123DC");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
	
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
		assertTrue(postResponse.getStatusCode().is2xxSuccessful());

		assertEquals("400 BAD_REQUEST", ((Status) postResponse.getBody()).getStatusCode());
		assertEquals("Not a valid Limit", ((Status) postResponse.getBody()).getMessage());
	}
	
	
	@Test
	public void testGetAllCreditCard() {
		CreditCardInfoData input = new CreditCardInfoData();
			
		input.setName("Tester Test1");
		input.setCardNumber("1111222233334444");
		input.setCardLimit("1000");

		ResponseEntity<Status> postResponse = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
		
		CreditCardInfoData input1 = new CreditCardInfoData();
		
		input1.setName("Tester Test1");
		input1.setCardNumber("1111222233334444");
		input1.setCardLimit("1000");

		ResponseEntity<Status> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
		
		CreditCardInfoData input2 = new CreditCardInfoData();
		
		input2.setName("Tester Test1");
		input2.setCardNumber("1111222233334444");
		input2.setCardLimit("1000");

		ResponseEntity<Status> postResponse2 = restTemplate.postForEntity(getRootUrl() + "/creditcards/add", input,
				Status.class);
			
		ResponseEntity<List<CreditCardInfoData>> getResponse =
		        restTemplate.exchange(getRootUrl() + "/creditcards/getall",
		            HttpMethod.GET, null, new ParameterizedTypeReference<List<CreditCardInfoData>>() {
		            });
		List<CreditCardInfoData> listCreditCardInfo = getResponse.getBody();
		
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
		assertTrue(getResponse.getStatusCode().is2xxSuccessful());
		
		assertTrue(listCreditCardInfo.size() == 3);
	}
	
	@Test
	public void testGetAllCreditCardEmpty() {
	
		ResponseEntity<List<CreditCardInfoData>> getResponse =
		        restTemplate.exchange(getRootUrl() + "/creditcards/getall",
		            HttpMethod.GET, null, new ParameterizedTypeReference<List<CreditCardInfoData>>() {
		            });
		List<CreditCardInfoData> listCreditCardInfo = getResponse.getBody();
		
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
		assertTrue(getResponse.getStatusCode().is2xxSuccessful());
		
		assertTrue(listCreditCardInfo.size() == 0);
	}
}
