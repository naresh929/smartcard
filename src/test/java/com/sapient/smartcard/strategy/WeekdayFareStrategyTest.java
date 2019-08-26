/**
 * 
 */
package com.sapient.smartcard.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author narkumar8
 *
 */
public class WeekdayFareStrategyTest {

	
	WeekdayFareStrategy weekDayStrategy;
	
	int stationTravelled;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		weekDayStrategy = new WeekdayFareStrategy();
		stationTravelled = 4;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sapient.smartcard.strategy.WeekdayFareStrategy#getFarePerStation(int)}.
	 */
	@Test
	public void testGetFarePerStation() {
		double travelFare = weekDayStrategy.getTravelFare(stationTravelled);
		assertEquals(28, travelFare,0);
	}
	
	/**
	 * Test method for {@link com.sapient.smartcard.strategy.WeekdayFareStrategy#getFarePerStation(int)}.
	 */
	@Test
	public void testGetFarePerStation_exitSameStation() {
		stationTravelled = 0;
		double travelFare = weekDayStrategy.getTravelFare(stationTravelled);
		assertEquals(0, travelFare,0);
	}
}
