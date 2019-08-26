/**
 * 
 */
package com.sapient.smartcard.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sapient.smartcard.beans.SmartCard;

/**
 * @author narkumar8
 *
 */
@Component
public class SmartCardRepository {

	public Set<SmartCard> smartCardList = new HashSet<>();

	public Set<SmartCard> getCardList() {
		return smartCardList;
	}

	public SmartCard addSmartCard(SmartCard smartCard) {
		smartCardList.add(smartCard);
		return smartCard;
	}

	public SmartCard removeSmartCard(SmartCard smartCard) {
		smartCardList.remove(smartCard);
		return smartCard;
	}
}
