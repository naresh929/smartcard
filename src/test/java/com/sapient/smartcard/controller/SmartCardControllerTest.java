/**
 * 
 */
package com.sapient.smartcard.controller;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.SmartCard;
import com.sapient.smartcard.constant.Constant;
import com.sapient.smartcard.service.SmartCardService;

/**
 * @author narkumar8
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SmartCardController.class)
public class SmartCardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	Set<SmartCard> cardList;

	@MockBean
	SmartCardService cardService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cardList = new HashSet<>();
		cardList.add(SmartCard.builder().balance(100).cardHolderName("rahul").cardId(5421).destinationStation(null)
				.sourceStation(null).build());
		cardList.add(SmartCard.builder().balance(102).cardHolderName("saroj").cardId(5422).destinationStation(null)
				.sourceStation(null).build());
		cardList.add(SmartCard.builder().balance(220).cardHolderName("mohan").cardId(5423).destinationStation(null)
				.sourceStation(null).build());
		cardList.add(SmartCard.builder().balance(120).cardHolderName("manoj").cardId(5424).destinationStation(null)
				.sourceStation(null).build());

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#getSmartCards()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSmartCards_Sucess() throws Exception {
		when(cardService.getSmartCards()).thenReturn(cardList);
		RequestBuilder request = MockMvcRequestBuilders.get("/cards").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
				.json("[{cardId:5421,balance:100.0,sourceStation:null,destinationStation:null,cardHolderName:rahul},"
						+ "{cardId:5422,balance:102.0,sourceStation:null,destinationStation:null,cardHolderName:saroj},"
						+ "{cardId:5423,balance:220.0,sourceStation:null,destinationStation:null,cardHolderName:mohan},"
						+ "{cardId:5424,balance:120.0,sourceStation:null,destinationStation:null,cardHolderName:manoj}]"))
				.andReturn();
		verify(cardService, times(1)).getSmartCards();
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#getSmartCards()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSmartCards_NoCardFoundInSystem() throws Exception {
		when(cardService.getSmartCards()).thenThrow(new CardNotFoundException("No Card Found in System::"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cards").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'No Card Found in System::'}")).andReturn();
		verify(cardService, times(1)).getSmartCards();
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#activateSmartCard(java.lang.String, double)}.
	 * 
	 * @throws Exception
	 * @throws SmartCardCustomException
	 */
	@Test
	public void testActivateSmartCard_Sucess() throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "mohan");
		json.put("amount", "200");
		String jsonString = json.toString();
		
		when(cardService.activateSmartCard(anyString(), anyDouble())).thenReturn(SmartCard.builder().balance(100)
				.cardHolderName("rahul").cardId(5421).destinationStation(null).sourceStation(null).build());
		RequestBuilder request = MockMvcRequestBuilders.post("/purchasecard")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"{cardId:5421, balance:100.0, sourceStation:null, destinationStation:null, cardHolderName:rahul}"))
				.andReturn();
		verify(cardService, times(1)).activateSmartCard(anyString(), anyDouble());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#activateSmartCard(java.lang.String, double)}.
	 * 
	 * @throws Exception
	 * @throws SmartCardCustomException
	 */
	@Test
	public void testActivateSmartCard_MinimumAmountCondition() throws Exception {
		JSONObject json = new JSONObject();
		json.put("name", "sachin");
		json.put("amount", "20");
		String jsonString = json.toString();
		when(cardService.activateSmartCard(anyString(), anyDouble())).thenThrow(new MinimumBalanceException(
				"Require minimum amount " + Constant.MINIMUM_BALANCE + " to activate new card"));
		RequestBuilder request = MockMvcRequestBuilders.post("/purchasecard")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString);
		mockMvc.perform(request).andExpect(status().isNotAcceptable())
				.andExpect(content().json("{message:'Require minimum amount 50.0 to activate new card'}")).andReturn();
		verify(cardService, times(1)).activateSmartCard(anyString(), anyDouble());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#rechargeSmartCard(int, double)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRechargeSmartCard_Sucess() throws Exception {
		when(cardService.rechargeSmartCard(anyInt(), anyDouble())).thenReturn(SmartCard.builder().balance(100)
				.cardHolderName("rahul").cardId(5421).destinationStation(null).sourceStation(null).build());
		RequestBuilder request = MockMvcRequestBuilders.put("/recharge/5421/20").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"{cardId:5421, balance:100.0, sourceStation:null, destinationStation:null, cardHolderName:rahul}"))
				.andReturn();
		verify(cardService, times(1)).rechargeSmartCard(anyInt(), anyDouble());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#rechargeSmartCard(int, double)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRechargeSmartCard_CardNotFound() throws Exception {
		when(cardService.rechargeSmartCard(anyInt(), anyDouble()))
				.thenThrow(new CardNotFoundException("Card Not Found for card ID::5422"));
		RequestBuilder request = MockMvcRequestBuilders.put("/recharge/5422/20").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'Card Not Found for card ID::5422'}")).andReturn();
		verify(cardService, times(1)).rechargeSmartCard(anyInt(), anyDouble());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#getCardBalance(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCardBalance_Sucess() throws Exception {
		when(cardService.getCardBalance(5421)).thenReturn(100.0);
		RequestBuilder request = MockMvcRequestBuilders.get("/balance/5421").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("{\"card Balance::card ID::5421\":100.0}")).andReturn();
		verify(cardService, times(1)).getCardBalance(anyInt());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#getCardBalance(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetCardBalance_CardNotFound() throws Exception {
		when(cardService.getCardBalance(anyInt()))
				.thenThrow(new CardNotFoundException("Card Not Found for card ID::5422"));
		RequestBuilder request = MockMvcRequestBuilders.get("/balance/5422").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'Card Not Found for card ID::5422'}")).andReturn();
		verify(cardService, times(1)).getCardBalance(anyInt());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#deleteCard(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteCard_Sucess() throws Exception {
		when(cardService.deactivateSmartCard(anyInt())).thenReturn("Smart Card Deactivated Sucessfully::");
		RequestBuilder request = MockMvcRequestBuilders.delete("/invalidatecard/5421").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{Message:'Smart Card Deactivated Sucessfully::'}")).andReturn();
		verify(cardService, times(1)).deactivateSmartCard(anyInt());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#deleteCard(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeleteCard_Sucess_CardNotFound() throws Exception {
		when(cardService.deactivateSmartCard(anyInt())).thenThrow(
				new CardNotFoundException("No Card Found in System with card ID::5421::uri=/invalidatecard/5421"));
		RequestBuilder request = MockMvcRequestBuilders.delete("/invalidatecard/5421").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content()
						.json("{message:'No Card Found in System with card ID::5421::uri=/invalidatecard/5421'}"))
				.andReturn();
		verify(cardService, times(1)).deactivateSmartCard(anyInt());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeIn(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeIn_Sucess() throws Exception {
		when(cardService.cardSwipeIn(anyInt(), anyString())).thenReturn(SmartCard.builder().balance(100)
				.cardHolderName("rahul").cardId(5421).destinationStation(null).sourceStation("A4").build());
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipein/5421/A4").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
				.json("{cardId:5421, balance:100.0, sourceStation:A4, destinationStation:null, cardHolderName:rahul}"))
				.andReturn();
		verify(cardService, times(1)).cardSwipeIn(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeIn(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeIn_BalanceBelowMinimumFare() throws Exception {
		when(cardService.cardSwipeIn(anyInt(), anyString())).thenThrow(new MinimumBalanceException(
				"Required minimum amount to start journey" + Constant.MINIMUM_SWIPEIN_BALANCE));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipein/5421/A4").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotAcceptable())
				.andExpect(content().json("{message:'Required minimum amount to start journey5.5'}")).andReturn();
		verify(cardService, times(1)).cardSwipeIn(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeIn(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeIn_InvalidSwipeIn() throws Exception {
		when(cardService.cardSwipeIn(anyInt(), anyString()))
				.thenThrow(new InvalidSwipeException("Invalid Journey, Smart card is already swipe in for travel"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipein/5421/A4").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isBadRequest())
				.andExpect(content().json("{message:'Invalid Journey, Smart card is already swipe in for travel'}"))
				.andReturn();
		verify(cardService, times(1)).cardSwipeIn(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeIn(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeIn_InvalidSourceStation() throws Exception {
		when(cardService.cardSwipeIn(anyInt(), anyString()))
				.thenThrow(new StationNotFoundException("Entered check in Station is not a valid station::"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipein/5421/A52").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'Entered check in Station is not a valid station::'}")).andReturn();
		verify(cardService, times(1)).cardSwipeIn(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeIn(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeIn_CardNotFound() throws Exception {
		when(cardService.cardSwipeIn(anyInt(), anyString()))
				.thenThrow(new CardNotFoundException("No Card Found in System with card ID::5421"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipein/5421/A5").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'No Card Found in System with card ID::5421'}")).andReturn();
		verify(cardService, times(1)).cardSwipeIn(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeOut(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeOut_Sucess() throws Exception {
		when(cardService.cardSwipeOut(anyInt(), anyString())).thenReturn(SmartCard.builder().balance(100)
				.cardHolderName("rahul").cardId(5421).destinationStation("A6").sourceStation("A4").build());
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipeout/5421/A4").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json(
						"{cardId:5421, balance:100.0, sourceStation:A4, destinationStation:A6, cardHolderName:rahul}"))
				.andReturn();
		verify(cardService, times(1)).cardSwipeOut(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeOut(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeOut_CardNotSwipeIn() throws Exception {
		when(cardService.cardSwipeOut(anyInt(), anyString()))
				.thenThrow(new InvalidSwipeException("Invalid Journey, Smart card is not swipe in for travel"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipeout/5421/A4").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isBadRequest())
				.andExpect(content().json("{message:'Invalid Journey, Smart card is not swipe in for travel'}"))
				.andReturn();
		verify(cardService, times(1)).cardSwipeOut(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeOut(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeOut_InvalidDestinationStation() throws Exception {
		when(cardService.cardSwipeOut(anyInt(), anyString()))
				.thenThrow(new StationNotFoundException("Entered check out Station is not a valid station::"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipeout/5421/A52")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'Entered check out Station is not a valid station::'}"))
				.andReturn();
		verify(cardService, times(1)).cardSwipeOut(anyInt(), anyString());
	}

	/**
	 * Test method for
	 * {@link com.sapient.smartcard.controller.SmartCardController#cardSwipeOut(int, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCardSwipeOut_CardNotFound() throws Exception {
		when(cardService.cardSwipeOut(anyInt(), anyString()))
				.thenThrow(new CardNotFoundException("No Card Found in System with card ID::5421"));
		RequestBuilder request = MockMvcRequestBuilders.get("/cardswipeout/5421/A5").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isNotFound())
				.andExpect(content().json("{message:'No Card Found in System with card ID::5421'}")).andReturn();
		verify(cardService, times(1)).cardSwipeOut(anyInt(), anyString());
	}
}