/**
 * 
 */
package com.sapient.smartcard.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author narkumar8
 *
 */
public class PurchaseDTO {
	
	private String name;
	private double amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public PurchaseDTO(String name, double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public PurchaseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
