/**
 * 
 */
package com.sapient.smartcard.strategy;

import com.sapient.smartcard.constant.Constant;

/**
 * @author narkumar8
 *
 */

public class WeekendFareStrategy implements FareStrategy {
	
	@Override
	public double getTravelFare(int stationTravelled) {	
		return Constant.WEEKEND_CHARGES*stationTravelled;
	}

}
