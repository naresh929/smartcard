/**
 * 
 */
package com.sapient.smartcard.common;

import java.util.Arrays;

/**
 * @author narkumar8
 *
 */
public enum MetroStations {
	A1(1, "A1"), A2(2, "A2"), A3(3, "A3"), A4(4, "A4"), A5(5, "A5"), A6(6, "A6"), A7(7, "A7"), A8(8, "A8"), A9(9, "A9"),
	A10(10, "A10");
	String stationName;
	int stationNo;

	MetroStations(int stationNo, String stationName) {
		this.stationName = stationName;
		this.stationNo = stationNo;
	}

	public String getName() {
		return this.stationName;
	}

	public static MetroStations getStation(String stationName) {
		return Arrays.asList(MetroStations.values()).stream().filter(e -> {
			return e.stationName.equals(stationName);
		}).findAny().get();
	}

	public static int distance(String source, String destination) {
		return Math.abs(MetroStations.valueOf(destination).ordinal() - MetroStations.valueOf(source).ordinal());
	}
}
