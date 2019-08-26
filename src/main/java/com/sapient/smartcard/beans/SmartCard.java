/**
 * 
 */
package com.sapient.smartcard.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author narkumar8
 *
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmartCard {
	
	private int cardId;
	private double balance;
	private String sourceStation;
	private String destinationStation;
	private String cardHolderName;
	
//	public boolean equals(Object obj) {
//		if(obj==this)
//			return true;
//		if(!(obj instanceof SmartCard))
//			return false;
//		SmartCard card = (SmartCard)obj;
//		return this.cardId == card.getCardId();
//	}
}
