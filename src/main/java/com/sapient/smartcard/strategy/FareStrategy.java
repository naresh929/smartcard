/**
 * 
 */
package com.sapient.smartcard.strategy;

/**
 * @author narkumar8
 *
 */
public interface FareStrategy {
	
	/**
	 * @param stationTravelled
	 * @return double
	 */
	public double getTravelFare(int stationTravelled);
}
