/**
 * 
 */
package com.sapient.smartcard.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.SmartCard;
import com.sapient.smartcard.repository.SmartCardRepository;

/**
 * @author narkumar8
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartCardServiceImplTest {
//
//	@InjectMocks
//	SmartCardServiceImpl smartCardService;
//
//	Set<SmartCard> cardList;
//
//	@Mock
//	SmartCardRepository smartCardRepo;
//	
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		cardList = new HashSet<>();
//		cardList.add(SmartCard.builder().balance(100).cardHolderName("rahul").cardId(5421).destinationStation(null)
//				.sourceStation(null).build());
//		cardList.add(SmartCard.builder().balance(102).cardHolderName("saroj").cardId(5422).destinationStation(null)
//				.sourceStation("A1").build());
//		cardList.add(SmartCard.builder().balance(3).cardHolderName("mohan").cardId(5423).destinationStation(null)
//				.sourceStation(null).build());
//		cardList.add(SmartCard.builder().balance(120).cardHolderName("manoj").cardId(5424).destinationStation(null)
//				.sourceStation("A2").build());
//		cardList.add(SmartCard.builder().balance(120).cardHolderName("sachin").cardId(5425).destinationStation(null)
//				.sourceStation(null).build());
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#getSmartCards(java.lang.String, double)}.
//	 */
//	@Test
//	public void testgetSmartCards_Sucess() throws CardNotFoundException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		int cardNumbers = smartCardService.getSmartCards().size();
//		assertEquals(5, cardNumbers);
//		verify(smartCardRepo, times(2)).getCardList();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#getSmartCards(java.lang.String, double)}.
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testgetSmartCards_CardNotFound() throws CardNotFoundException {
//		cardList.clear();
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.getSmartCards().size();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#activateSmartCard(java.lang.String, double)}.
//	 */
//
//	@Test
//	public void testActivateSmartCard_Success() throws Exception {
//		SmartCard smartCard = smartCardService.activateSmartCard("aman", 140);
//		assertNotNull(smartCard);
//		assertEquals(140, smartCard.getBalance(), 0);
//		verify(smartCardRepo, times(1)).addSmartCard(smartCard);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#activateSmartCard(java.lang.String, double)}.
//	 * 
//	 * @throws MinimumBalanceException
//	 */
//
//	@Test(expected = MinimumBalanceException.class)
//	public void testActivateSmartCard_MinimumAmountException() throws Exception {
//		smartCardService.activateSmartCard("aman", 40);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#rechargeSmartCard(int, double)}.
//	 * @throws MinimumBalanceException 
//	 */
//	@Test
//	public void testRechargeSmartCard_Success() throws CardNotFoundException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		SmartCard smartCard = smartCardService.rechargeSmartCard(5421, 200);
//		assertNotNull(smartCard);
//		assertEquals(300, smartCard.getBalance(), 0);
//		verify(smartCardRepo, times(1)).getCardList();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#rechargeSmartCard(int, double)}.
//	 * 
//	 * @throws CardNotFoundException
//	 * @throws MinimumBalanceException 
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testRechargeSmartCard_NoCardFound() throws CardNotFoundException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.rechargeSmartCard(1234, 200);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeIn(int, java.lang.String)}.
//	 */
//	@Test
//	public void testCardSwipeIn_Success()
//			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		SmartCard cardSwipeIn = smartCardService.cardSwipeIn(5425, "A5");
//		assertNotNull(cardSwipeIn);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeIn(int, java.lang.String)}.
//	 * 
//	 * @throws CardNotFoundException
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testCardSwipeIn_NoCardFound()
//			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeIn(5429, "A1");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeIn(int, java.lang.String)}.
//	 * 
//	 * @throws StationNotFoundException
//	 */
//	@Test(expected = StationNotFoundException.class)
//	public void testCardSwipeIn_StationNotFound()
//			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeIn(5421, "A18");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeIn(int, java.lang.String)}.
//	 * 
//	 * @throws InvalidSwipeException
//	 */
//	@Test(expected = InvalidSwipeException.class)
//	public void testCardSwipeIn_InvalidSwipe()
//			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeIn(5424, "A18");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeIn(int, java.lang.String)}.
//	 * 
//	 * @throws MinimumBalanceException
//	 */
//	@Test(expected = MinimumBalanceException.class)
//	public void testCardSwipeIn_MinimumBalance()
//			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeIn(5423, "A18");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeOut(int, java.lang.String)}.
//	 */
//	@Test
//	public void testCardSwipeOut_Success()
//			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		SmartCard cardSwipeOut = smartCardService.cardSwipeOut(5422, "A9");
//		assertNotNull(cardSwipeOut);
//		verify(smartCardRepo, times(1)).getCardList();
//		assertEquals(46.0, cardSwipeOut.getBalance(), 0);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeOut(int, java.lang.String)}.
//	 * 
//	 * @throws CardNotFoundException
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testCardSwipeOut_NoCardFound()
//			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeOut(5427, "A9");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeOut(int, java.lang.String)}.
//	 * 
//	 * @throws StationNotFoundException
//	 */
//	@Test(expected = StationNotFoundException.class)
//	public void testCardSwipeOut_StationNotFound()
//			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeOut(5422, "A92");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#cardSwipeOut(int, java.lang.String)}.
//	 * 
//	 * @throws InvalidSwipeException
//	 */
//	@Test(expected = InvalidSwipeException.class)
//	public void testCardSwipeOut_InvalidSwipe()
//			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.cardSwipeOut(5423, "A9");
//
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#calculateTravelFare(com.sapient.smartcard.beans.SmartCard)}.
//	 * 
//	 * @throws MinimumBalanceException
//	 */
//	@Test
//	public void testCalculateTravelFare_Sucess() throws MinimumBalanceException {
//		SmartCard smartCard = SmartCard.builder().balance(200).cardHolderName("rahul").cardId(5421)
//				.destinationStation("A1").sourceStation("A5").build();
//		smartCard = smartCardService.calculateTravelFare(smartCard);
//		assertEquals(172, smartCard.getBalance(), 0);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#calculateTravelFare(com.sapient.smartcard.beans.SmartCard)}.
//	 * 
//	 * @throws MinimumBalanceException
//	 */
//	@Test(expected = MinimumBalanceException.class)
//	public void testCalculateTravelFare_LowBalanceInCard() throws MinimumBalanceException {
//		SmartCard smartCard = SmartCard.builder().balance(20).cardHolderName("rahul").cardId(5421)
//				.destinationStation("A1").sourceStation("A9").build();
//		smartCard = smartCardService.calculateTravelFare(smartCard);
//		
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#calculateTravelFare(com.sapient.smartcard.beans.SmartCard)}.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected = Exception.class)
//	public void testCalculateTravelFare_InvalidSourceStation() throws Exception {
//		SmartCard smartCard = SmartCard.builder().balance(20).cardHolderName("rahul").cardId(5421)
//				.destinationStation("A15").sourceStation("A9").build();
//		smartCard = smartCardService.calculateTravelFare(smartCard);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#calculateTravelFare(com.sapient.smartcard.beans.SmartCard)}.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected = Exception.class)
//	public void testCalculateTravelFare_InvalidDestinationStation() throws Exception {
//		SmartCard smartCard = SmartCard.builder().balance(20).cardHolderName("rahul").cardId(5421)
//				.destinationStation("A1").sourceStation("A93").build();
//		smartCard = smartCardService.calculateTravelFare(smartCard);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#getCardBalance(int)}.
//	 * 
//	 * @throws CardNotFoundException
//	 */
//	@Test
//	public void testGetCardBalance_Sucess() throws CardNotFoundException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		double cardBalance = smartCardService.getCardBalance(5421);
//		assertEquals(100, cardBalance, 0);
//		verify(smartCardRepo, times(1)).getCardList();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#getCardBalance(int)}.
//	 * 
//	 * @throws CardNotFoundException
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testGetCardBalance_CardNotFound() throws CardNotFoundException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.getCardBalance(5427);
//	}
//
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#deactivateSmartCard(int)}.
//	 */
//	@Test
//	public void testDeactivateSmartCard_Sucess() throws CardNotFoundException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		String deactivateSmartCard = smartCardService.deactivateSmartCard(5421);
//		assertEquals("Smart Card Deactivated Sucessfully::card ID::5421", deactivateSmartCard);
//		verify(smartCardRepo, times(1)).getCardList();
//		verify(smartCardRepo, times(1)).removeSmartCard(any());
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.sapient.smartcard.service.SmartCardServiceImpl#deactivateSmartCard(int)}.
//	 * 
//	 * @throws CardNotFoundException
//	 */
//	@Test(expected = CardNotFoundException.class)
//	public void testDeactivateSmartCard_CardNotFound() throws CardNotFoundException {
//		when(smartCardRepo.getCardList()).thenReturn(cardList);
//		smartCardService.deactivateSmartCard(5426);
//	}
}
