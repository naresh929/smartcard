/**
 * 
 */
package com.sapient.smartcard.service;

import java.util.Set;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.SmartCard;

/**
 * @author narkumar8
 *
 */
public interface SmartCardService {

	/**
	 * @param cardUserName
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public SmartCard activateSmartCard(String cardUserName, double amount) throws Exception;

	/**
	 * @param cardId
	 * @param amount
	 * @return
	 * @throws CardNotFoundException
	 * @throws MinimumBalanceException
	 */
	public SmartCard rechargeSmartCard(int cardId, double amount) throws CardNotFoundException, MinimumBalanceException;

	/**
	 * @param cardId
	 * @param sourceStation
	 * @return
	 * @throws CardNotFoundException
	 * @throws StationNotFoundException
	 * @throws InvalidSwipeException
	 * @throws MinimumBalanceException
	 */
	public SmartCard cardSwipeIn(int cardId, String sourceStation)
			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException;

	/**
	 * @param cardid
	 * @param destinationStation
	 * @return
	 * @throws MinimumBalanceException
	 * @throws CardNotFoundException
	 * @throws StationNotFoundException
	 * @throws InvalidSwipeException
	 */
	public SmartCard cardSwipeOut(int cardid, String destinationStation)
			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException;

	/**
	 * @param smartcard
	 * @return
	 * @throws MinimumBalanceException
	 */
	public SmartCard calculateTravelFare(SmartCard smartcard) throws MinimumBalanceException;

	/**
	 * @return
	 * @throws CardNotFoundException
	 */
	public Set<SmartCard> getSmartCards() throws CardNotFoundException;

	/**
	 * @param cardId
	 * @return
	 * @throws CardNotFoundException
	 */
	public double getCardBalance(int cardId) throws CardNotFoundException;

	/**
	 * @param cardId
	 * @return
	 * @throws CardNotFoundException
	 */
	public String deactivateSmartCard(int cardId) throws CardNotFoundException;
}
