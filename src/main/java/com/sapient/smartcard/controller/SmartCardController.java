/**
 * 
 */
package com.sapient.smartcard.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.PurchaseDTO;
import com.sapient.smartcard.beans.SmartCard;
import com.sapient.smartcard.service.SmartCardService;

/**
 * @author narkumar8
 *
 */

@RestController
public class SmartCardController {

	@Autowired
	private SmartCardService smartCardService;

	/**
	 * @return
	 * @throws SmartCardCustomException
	 */
	@GetMapping("/cards")
	public Set<SmartCard> getSmartCards() throws CardNotFoundException {
		return smartCardService.getSmartCards();
	}

	/**
	 * @param name
	 * @param amount
	 * @return
	 * @throws Exception
	 */

	RestTemplate template = new RestTemplate();
	
	@PostMapping("/purchasecard")
	public SmartCard activateSmartCard(@RequestBody PurchaseDTO purchase) throws Exception {
		return smartCardService.activateSmartCard(purchase.getName(), purchase.getAmount());
	}

	/**
	 * @param cardid
	 * @param amount
	 * @return
	 * @throws MinimumBalanceException 
	 * @throws SmartCardCustomException
	 */

	@PutMapping("/recharge/{cardid}/{amount}")
	public SmartCard rechargeSmartCard(@PathVariable int cardid, @PathVariable double amount)
			throws CardNotFoundException, MinimumBalanceException {
		return smartCardService.rechargeSmartCard(cardid, amount);
	}

	/**
	 * @param cardid
	 * @return
	 * @throws SmartCardCustomException
	 */
	@GetMapping("/balance/{cardid}")
	public Map<String, Double> getCardBalance(@PathVariable int cardid) throws CardNotFoundException {
		double balance;
		balance = smartCardService.getCardBalance(cardid);
		Map<String, Double> cardBalance = new HashMap<>();
		cardBalance.put("card Balance::card ID::" + cardid, balance);
		return cardBalance;
	}

	/**
	 * @param cardid
	 * @return
	 * @throws SmartCardCustomException
	 */
	@DeleteMapping("/invalidatecard/{cardid}")
	public Map<String, String> deleteCard(@PathVariable int cardid) throws CardNotFoundException {
		String message = null;
		Map<String, String> deleteMessage = new HashMap<>();
		message = smartCardService.deactivateSmartCard(cardid);
		deleteMessage.put("Message", message);
		return deleteMessage;
	}

	/**
	 * @param cardid
	 * @param station
	 * @return
	 * @throws SmartCardCustomException
	 * @throws MinimumBalanceException
	 * @throws InvalidSwipeException
	 * @throws StationNotFoundException
	 * @throws CardNotFoundException
	 */
	@GetMapping("/cardswipein/{cardid}/{station}")
	public SmartCard cardSwipeIn(@PathVariable int cardid, @PathVariable String station)
			throws MinimumBalanceException, InvalidSwipeException, StationNotFoundException, CardNotFoundException {
		return smartCardService.cardSwipeIn(cardid, station);
	}

	/**
	 * @param cardid
	 * @param station
	 * @return
	 * @throws SmartCardCustomException
	 * @throws MinimumBalanceException
	 * @throws StationNotFoundException
	 * @throws CardNotFoundException
	 * @throws InvalidSwipeException
	 */
	@GetMapping("/cardswipeout/{cardid}/{station}")
	public SmartCard cardSwipeOut(@PathVariable int cardid, @PathVariable String station)
			throws MinimumBalanceException, StationNotFoundException, CardNotFoundException, InvalidSwipeException {
		return smartCardService.cardSwipeOut(cardid, station);
	}
}
