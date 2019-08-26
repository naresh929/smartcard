/**
 * 
 */
package com.sapient.smartcard.strategy;

/**
 * @author narkumar8
 *
 */
public class Context {
	
	private FareStrategy strategy;
	
	public Context(FareStrategy strategy) {
		this.strategy = strategy;
	}
	
	public double executeStrategy(int stationTravelled) {
		return strategy.getTravelFare(stationTravelled);
	}
}
