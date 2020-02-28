/**
 * 
 */
package com.sapient.smartcard.controller;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.smartcard.SmartcardApplication;
import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.beans.PurchaseDTO;
import com.sapient.smartcard.beans.SmartCard;

/**
 * @author narkumar8
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartcardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartCardControllerTestIT {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetSmartCards_NoCardFoundInSystem() throws CardNotFoundException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<SmartCard> exchange = restTemplate.exchange(createURLWithPort("/cards"), HttpMethod.GET, entity,SmartCard.class);
		assertEquals(404,exchange.getStatusCodeValue());
	}
	
	@Test
	public void testActivateSmartCard_MinimumAmountCondition() throws Exception {
		PurchaseDTO purchase = new PurchaseDTO("naresh", 20);
		ResponseEntity<SmartCard> exchange = restTemplate.postForEntity(createURLWithPort("/purchasecard"), purchase,SmartCard.class);
		assertEquals(406,exchange.getStatusCodeValue());
	}
	
	
	@Test
	public void testActivateSmartCard_Sucess() throws Exception {
		PurchaseDTO purchase = new PurchaseDTO("naresh", 200);
		ResponseEntity<SmartCard> exchange = restTemplate.postForEntity(createURLWithPort("/purchasecard"), purchase,SmartCard.class);
		assertEquals(200,exchange.getStatusCodeValue());
		//assertEquals("naresh", exchange.getBody().getCardHolderName());
	}
	
	@Test
	public void testGetCardBalance_CardNotFound() throws Exception {
		ResponseEntity<Map> responseEntity = restTemplate.getForEntity(createURLWithPort("/balance/12345"), Map.class);
		assertEquals(404,responseEntity.getStatusCodeValue());
		System.out.println("Hello");
		assertEquals("No Card Found in System with card ID::12345", responseEntity.getBody().get("message"));
	}
	
	@Test
	public void testCardSwipeIn_CardNotFound() throws Exception {
		ResponseEntity<SmartCard> responseEntity = restTemplate.getForEntity(createURLWithPort("/cardswipein/12345/A2"), SmartCard.class);
		assertEquals(404,responseEntity.getStatusCodeValue());
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}