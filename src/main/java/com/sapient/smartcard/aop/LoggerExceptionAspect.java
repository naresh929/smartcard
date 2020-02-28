/**
 * 
 */
package com.sapient.smartcard.aop;

import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.sapient.smartcard.Exception.CardNotFoundException;
import com.sapient.smartcard.Exception.InvalidSwipeException;
import com.sapient.smartcard.Exception.MinimumBalanceException;
import com.sapient.smartcard.Exception.StationNotFoundException;
import com.sapient.smartcard.beans.PurchaseDTO;
import com.sapient.smartcard.beans.SmartCard;

/**
 * @author narkumar8
 *
 */
@Aspect
@Configuration
public class LoggerExceptionAspect {

	final static Logger logger = LoggerFactory.getLogger(LoggerExceptionAspect.class);

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.getSmartCards(..))")
	public Object aroundControllerGetSmartCards(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Fething the registered card from system::");
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		logger.info("SuccessFully returned Registered card from system::");
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.getSmartCards(..))")
	public Object aroundServiceGetSmartCards(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Fething the registered card from Service::");
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (CardNotFoundException ex) {
			logger.error("Exception in fething cards from Service::");
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.activateSmartCard(..))")
	public Object aroundControllerActivateSmartCard(ProceedingJoinPoint joinPoint) throws Throwable {
		PurchaseDTO purchaseDTO = (PurchaseDTO) joinPoint.getArgs()[0];
		logger.info("Request for new smart card::applicant name::" + purchaseDTO.getName() + "::Initial amount::"
				+ purchaseDTO.getAmount());
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
			throw new MinimumBalanceException(ex.getMessage(), ex);
		}
		logger.info("New Smart Card activated::SmartCard::" + value);
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.activateSmartCard(..))")
	public Object aroundServiceActivateSmartCard(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Request for activating new smart card Service::applicant name::" + joinPoint.getArgs()[0]
				+ "::Initial amount::" + joinPoint.getArgs()[1]);
		SmartCard value = null;
		Optional<SmartCard> smartCard = null;
		try {
			value = (SmartCard) joinPoint.proceed();
			smartCard = Optional.of(value);
			if (smartCard.isPresent()) {
				logger.info("New Card activated and registered in system Service::");
			} else {
				logger.error("System Error while generating new Smart Card Service::");
			}
		} catch (Throwable ex) {
			logger.error("Minimum Balance Exception occured in Service::");
			throw new MinimumBalanceException(ex.getMessage(), ex);
		}
		return smartCard.get();
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.rechargeSmartCard(..))")
	public Object aroundControllerRechargeSmartCard(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		double amount = (double) joinPoint.getArgs()[1];
		logger.info("Request to recharge the smart card::Smart card ID::" + cardId + "::recharge amount::" + amount);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (CardNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		} catch (MinimumBalanceException ex) {
			logger.error("Recharge Amount should be positive::");
			throw new MinimumBalanceException(ex.getMessage(), ex);
		}
		logger.info("Recharge sucessfully::Smart card ID::" + cardId + "::Current balance::"
				+ ((SmartCard) value).getBalance());
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.rechargeSmartCard(..))")
	public Object aroundServiceRechargeSmartCard(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		double amount = (double) joinPoint.getArgs()[1];
		logger.info("Request to recharge the smart card Service::Smart card ID::" + cardId + "::recharge amount::"
				+ amount);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (CardNotFoundException ex) {
			logger.error("Exception in Recharge smart card process Service::");
			throw new CardNotFoundException(ex.getMessage(), ex);
		} catch (MinimumBalanceException ex) {
			logger.error("Recharge Amount should be positive::");
			throw new MinimumBalanceException(ex.getMessage(), ex);
		}
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.getCardBalance(..))")
	public Object aroundControllerGetCardBalance(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		logger.info("Request for smart card balance::card ID::" + cardId);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		logger.info("Smart Card Balance::card ID::" + cardId + "::card balance::" + value);
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.getCardBalance(..))")
	public Object aroundServiceGetCardBalance(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		logger.info("Request for smart card balance Service::card ID::" + cardId);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error("Exception in fetching smart card balance::card ID::" + cardId);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.deleteCard(..))")
	public Object aroundControllerDeleteCard(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		logger.info("Request for deactivating the smart card::card ID::" + cardId);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		logger.info("Smart Card deactivated sucessfully::card ID::" + cardId);
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.deactivateSmartCard(..))")
	public Object aroundServiceDeleteCard(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		logger.info("Request for deactivating the smart card Service::card ID::" + cardId);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (Throwable ex) {
			logger.error("No Card Found in System with card ID Service::" + cardId);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.cardSwipeIn(..))")
	public Object aroundControllerCardSwipeIn(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		String station = (String) joinPoint.getArgs()[1];
		logger.info("Request to swipe in smart card for journey::card ID::" + cardId + "::source station::" + station);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (MinimumBalanceException ex) {
			logger.error(ex.getMessage(), ex);
			throw new MinimumBalanceException(ex.getMessage(), ex);
		} catch (InvalidSwipeException ex) {
			logger.error(ex.getMessage(), ex);
			throw new InvalidSwipeException(ex.getMessage(), ex);
		} catch (StationNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
			throw new StationNotFoundException(ex.getMessage(), ex);
		} catch (CardNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		logger.info("Journey started::card ID::" + cardId + "::source station::" + station);
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.cardSwipeIn(..))")
	public Object aroundServiceCardSwipeIn(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		String station = (String) joinPoint.getArgs()[1];
		logger.info("Request to swipe in smart card for journey Service::card ID::" + cardId + "::source station::"
				+ station);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (MinimumBalanceException ex) {
			logger.error("Minimum Balance Exception in Swipe in smart card Service::");
			throw new MinimumBalanceException(ex.getMessage(), ex);
		} catch (InvalidSwipeException ex) {
			logger.error("Invalid Swipe Exception in Swipe in smart card Service::");
			throw new InvalidSwipeException(ex.getMessage(), ex);
		} catch (StationNotFoundException ex) {
			logger.error("Invalid Station Exception in Swipe in smart card Service::");
			throw new StationNotFoundException(ex.getMessage(), ex);
		} catch (CardNotFoundException ex) {
			logger.error("Card Not Found Exception in Swipe in smart card Service::");
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.controller.SmartCardController.cardSwipeOut(..))")
	public Object aroundControllerCardSwipeOut(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		String station = (String) joinPoint.getArgs()[1];
		logger.info("Request to swipe out smart card for journey::card ID::" + cardId + "::destination station::"
				+ station);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (MinimumBalanceException ex) {
			logger.error(ex.getMessage(), ex);
			throw new MinimumBalanceException(ex.getMessage(), ex);
		} catch (InvalidSwipeException ex) {
			logger.error(ex.getMessage(), ex);
			throw new InvalidSwipeException(ex.getMessage(), ex);
		} catch (StationNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
			throw new StationNotFoundException(ex.getMessage(), ex);
		} catch (CardNotFoundException ex) {
			logger.error(ex.getMessage(), ex);
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		logger.info("Request to swipe out smart card for journey Completed::card ID::" + cardId
				+ "::destination station::" + station);
		return value;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.sapient.smartcard.service.SmartCardServiceImpl.cardSwipeOut(..))")
	public Object aroundServiceCardSwipeOut(ProceedingJoinPoint joinPoint) throws Throwable {
		int cardId = (int) joinPoint.getArgs()[0];
		String station = (String) joinPoint.getArgs()[1];
		logger.info("Request to terminate journey Service::card ID::" + cardId + "::destination station::" + station);
		Object value = null;
		try {
			value = joinPoint.proceed();
		} catch (MinimumBalanceException ex) {
			logger.error("Minimum Balance Exception in Swipe out smart card Service::");
			throw new MinimumBalanceException(ex.getMessage(), ex);
		} catch (InvalidSwipeException ex) {
			logger.error("Invalid Swipe Exception in Swipe out smart card Service::");
			throw new InvalidSwipeException(ex.getMessage(), ex);
		} catch (StationNotFoundException ex) {
			logger.error("Invalid Station Exception in Swipe out smart card Service::");
			throw new StationNotFoundException(ex.getMessage(), ex);
		} catch (CardNotFoundException ex) {
			logger.error("Card Not Found Exception in Swipe out smart card Service::");
			throw new CardNotFoundException(ex.getMessage(), ex);
		}
		return value;
	}
}