/**
 * 
 */
package com.sapient.smartcard.strategy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author narkumar8
 *
 */
public class WeekendFareStrategyTest {
	
	WeekendFareStrategy weekendStrategy;
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
		stationTravelled = 4;
		weekendStrategy = new WeekendFareStrategy();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sapient.smartcard.strategy.WeekendFareStrategy#getFarePerStation(int)}.
	 */
	@Test
	public void testGetFarePerStation() {
		double travelfare = weekendStrategy.getTravelFare(stationTravelled);
		assertEquals(22, travelfare,0);
	}
	
	/**
	 * Test method for {@link com.sapient.smartcard.strategy.WeekendFareStrategy#getFarePerStation(int)}.
	 */
	@Test
	public void testGetFarePerStation_exitSameStation() {
		stationTravelled = 0;
		double travelfare = weekendStrategy.getTravelFare(stationTravelled);
		assertEquals(0, travelfare,0);
	}
}
