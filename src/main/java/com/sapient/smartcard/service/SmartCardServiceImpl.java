/**
 * 
 */
package com.sapient.smartcard.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.SmartCard;
import com.sapient.smartcard.common.MetroStations;
import com.sapient.smartcard.common.Utils;
import com.sapient.smartcard.constant.Constant;
import com.sapient.smartcard.repository.SmartCardRepository;
import com.sapient.smartcard.strategy.Context;
import com.sapient.smartcard.strategy.WeekdayFareStrategy;
import com.sapient.smartcard.strategy.WeekendFareStrategy;

/**
 * @author narkumar8
 *
 */
@Service
public class SmartCardServiceImpl implements SmartCardService {

	@Autowired
	SmartCardRepository smartCardRepo;

	@Override
	public String deactivateSmartCard(int cardId) throws CardNotFoundException {
		return smartCardRepo.getCardList().stream().filter(e -> e.getCardId() == cardId).findAny().map(e -> {
			smartCardRepo.removeSmartCard(e);
			return "Smart Card Deactivated Sucessfully::card ID::" + cardId;
		}).orElseThrow(() -> new CardNotFoundException("No Card Found in System with card ID::" + cardId));
	}

	@Override
	public SmartCard activateSmartCard(String cardHolderName, double amount) throws Exception {
		if (amount < Constant.MINIMUM_BALANCE) {
			throw new MinimumBalanceException(
					"Require minimum amount " + Constant.MINIMUM_BALANCE + " to activate new card");
		}
		int cardId = Utils.generateUniqueId();
		return Optional.of(new SmartCard(cardHolderName,amount,cardId,"","")).map(e -> {
					smartCardRepo.addSmartCard(e);
					return e;
				}).orElseThrow(
						() -> new Exception("Card cannot be activate due to System error, Please try after some time"));
	}

	@Override
	public SmartCard cardSwipeIn(int cardId, String sourceStation)
			throws CardNotFoundException, StationNotFoundException, InvalidSwipeException, MinimumBalanceException {
		Optional<SmartCard> smartCard = smartCardRepo.getCardList().stream().filter(e -> e.getCardId() == cardId)
				.findAny();
		if (smartCard.isPresent()) {
			if (smartCard.get().getBalance() < Constant.MINIMUM_SWIPEIN_BALANCE) {
				throw new MinimumBalanceException(
						"Required minimum amount to start journey " + Constant.MINIMUM_SWIPEIN_BALANCE);
			}
			if (smartCard.get().getSourceStation() != null && smartCard.get().getSourceStation() != "") {
				throw new InvalidSwipeException("Invalid Journey, Smart card is already swipe in for travel");
			}
			boolean validStations = Arrays.asList((MetroStations.values())).stream().map(e -> e.getName())
					.collect(Collectors.toList()).contains(sourceStation);
			if (validStations) {
				smartCard.get().setSourceStation(sourceStation);
				return smartCard.get();
			} else {
				throw new StationNotFoundException("Entered check-in Station is not a valid station::");
			}
		} else {
			throw new CardNotFoundException("Card Not Found in System with card ID::" + cardId);
		}
	}

	@Override
	public SmartCard cardSwipeOut(int cardId, String destinationStation)
			throws MinimumBalanceException, CardNotFoundException, StationNotFoundException, InvalidSwipeException {
		Optional<SmartCard> smartCard = smartCardRepo.getCardList().stream().filter(e -> e.getCardId() == cardId)
				.findAny();
		if (smartCard.isPresent()) {
			if (smartCard.get().getSourceStation() == null || smartCard.get().getSourceStation().equals("")) {
				throw new InvalidSwipeException("Invalid Journey, Smart card is not swipe in for travel");
			}
			boolean validStations = Arrays.asList((MetroStations.values())).stream().map(e -> e.getName())
					.collect(Collectors.toList()).contains(destinationStation);
			if (validStations) {
				smartCard.get().setDestinationStation(destinationStation);
				return calculateTravelFare(smartCard.get());
			} else {
				throw new StationNotFoundException("Entered check-out Station is not a valid station::");
			}
		} else {
			throw new CardNotFoundException("Card Not Found in System with card ID::" + cardId);
		}
	}

	@Override
	public SmartCard calculateTravelFare(SmartCard smartCard) throws MinimumBalanceException {
		double fare;
		double remainingBalance;
		double currentBalance = smartCard.getBalance();
		DayOfWeek day = LocalDate.now().getDayOfWeek();
		int stationTravelled = MetroStations.distance(smartCard.getSourceStation(), smartCard.getDestinationStation());
		if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
			fare = new Context(new WeekendFareStrategy()).executeStrategy(stationTravelled);
			remainingBalance = currentBalance - fare;
		} else {
			fare = new Context(new WeekdayFareStrategy()).executeStrategy(stationTravelled);
			remainingBalance = currentBalance - fare;
		}
		if (remainingBalance < 0) {
			throw new MinimumBalanceException("Unable to exit(low balance), Please Recharge the Card::");
		}
		smartCard.setDestinationStation("");
		smartCard.setSourceStation("");
		smartCard.setBalance(remainingBalance);
		return smartCard;
	}

	@Override
	public Set<SmartCard> getSmartCards() throws CardNotFoundException {
		if (smartCardRepo.getCardList().size() > 0)
			return smartCardRepo.getCardList();
		else {
			throw new CardNotFoundException("No Card Found in System::");
		}
	}

	@Override
	public double getCardBalance(int cardId) throws CardNotFoundException {
		return smartCardRepo.getCardList().stream().filter(e -> e.getCardId() == cardId).findAny()
				.map(e -> e.getBalance())
				.orElseThrow(() -> new CardNotFoundException("No Card Found in System with card ID::" + cardId));
	}

	@Override
	public SmartCard rechargeSmartCard(int cardId, double amount)
			throws CardNotFoundException, MinimumBalanceException {
		if (amount > 0) {
			return smartCardRepo.getCardList().stream().filter(e -> e.getCardId() == cardId).findAny().map(e -> {
				e.setBalance(e.getBalance() + amount);
				return e;
			}).orElseThrow(() -> new CardNotFoundException("Card Not Found for card ID::" + cardId));
		} else {
			throw new MinimumBalanceException("Recharge Amount should be positive::");
		}
	}
}
