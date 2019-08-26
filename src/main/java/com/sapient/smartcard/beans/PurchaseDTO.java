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
@Data
@AllArgsConstructor
public class PurchaseDTO {
	
	private String name;
	private double amount;
}
