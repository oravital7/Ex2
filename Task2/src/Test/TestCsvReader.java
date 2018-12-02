package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import File_format.CsvReader;
import org.junit.jupiter.api.Test;

class TestCsvReader {
	
CsvReader cR= new CsvReader("ExamplesFiles\\csvT.csv");	


	@Test
	void testGet_header() {
		ArrayList<String> cRheader =cR.get_header();
		String [] header = {"MAC", "SSID", "AuthMode", "FirstSeen", "Channel", "RSSI", "CurrentLatitude", "CurrentLongitude", "AltitudeMeters", "AccuracyMeters", "Type"};
		System.out.println(cR.get_header());
		for (int i = 0; i < header.length; i++) {
			assertEquals(cRheader.get(i), header[i]);
		}
	}

	@Test
	void testCheckCsv() {
		assertTrue(cR.checkCsv());
	}


	@Test
	void testGet_column() {
		assertEquals(cR.get_column(), 11);
	}



}
