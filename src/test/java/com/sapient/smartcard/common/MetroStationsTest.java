/**
 * 
 */
package com.sapient.smartcard.common;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author narkumar8
 *
 */
public class MetroStationsTest {
	
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
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Test method for {@link com.sapient.smartcard.common.MetroStations#getStation(java.lang.String)}.
	 */
	@Test
	public void testGetStation_Sucess() {
		String stationName = "A4";
		MetroStations station = MetroStations.getStation(stationName);
		assertEquals(stationName,station.getName());
	}
	
	/**
	 * Test method for {@link com.sapient.smartcard.common.MetroStations#getStation(java.lang.String)}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testGetStation_InvalidStationName() {
		String stationName = "A42";
		MetroStations.getStation(stationName);
	}

	/**
	 * Test method for {@link com.sapient.smartcard.common.MetroStations#distance(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDistance_Sucess() {
		String sourceStation = "A5";
		String destinationStation = "A1";
		int distance = MetroStations.distance(sourceStation, destinationStation);
		System.out.println("Distance::"+distance);
	}
	
	/**
	 * Test method for {@link com.sapient.smartcard.common.MetroStations#distance(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDistance_InvalidSourceStation() {
		String sourceStation = "A51";
		String destinationStation = "A8";
		MetroStations.distance(sourceStation, destinationStation);
	}
	
	/**
	 * Test method for {@link com.sapient.smartcard.common.MetroStations#distance(java.lang.String, java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDistance_InvalidDestinationStation() {
		String sourceStation = "A5";
		String destinationStation = "A82";
		MetroStations.distance(sourceStation, destinationStation);
	}
}
