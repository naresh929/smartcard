/**
 * 
 */
package com.sapient.smartcard.common;

import java.util.Random;

/**
 * @author narkumar8
 *
 */
public class Utils {

	public static int generateUniqueId() {
		Random random = new Random();
		int randNumber = random.nextInt(100000);
		return randNumber;
	}
}
