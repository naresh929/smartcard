/**
 * 
 */
package com.sapient.smartcard.strategy;

import com.sapient.smartcard.constant.Constant;

/**
 * @author narkumar8
 *
 */

public class WeekdayFareStrategy implements FareStrategy {
	

	@Override
	public double getTravelFare(int stationTravelled) {
		return Constant.WEEKDAY_CHARGES*stationTravelled;
	}

}
