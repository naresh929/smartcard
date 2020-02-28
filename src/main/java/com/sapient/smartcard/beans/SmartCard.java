/**
 * 
 */
package com.sapient.smartcard.beans;

import lombok.Builder;

/**
 * @author narkumar8
 *
 */
public class SmartCard {
	
	private int cardId;
	private double balance;
	private String sourceStation;
	private String destinationStation;
	private String cardHolderName;
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	@Override
	public String toString() {
		return "SmartCard [cardId=" + cardId + ", balance=" + balance + ", sourceStation=" + sourceStation
				+ ", destinationStation=" + destinationStation + ", cardHolderName=" + cardHolderName + "]";
	}
	public SmartCard(int cardId, double balance, String sourceStation, String destinationStation,
			String cardHolderName) {
		super();
		this.cardId = cardId;
		this.balance = balance;
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.cardHolderName = cardHolderName;
	}
	public SmartCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmartCard(String cardHolderName2, double amount, int cardId2, String destinationStation2,
			String cardHolderName3) {
		// TODO Auto-generated constructor stub
	}
	
//	public boolean equals(Object obj) {
//		if(obj==this)
//			return true;
//		if(!(obj instanceof SmartCard))
//			return false;
//		SmartCard card = (SmartCard)obj;
//		return this.cardId == card.getCardId();
//	}
}
