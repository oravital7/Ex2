package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import File_format.Csv2kml;
import GIS.Pdata;
import GIS.Project;

class TestCsv2Kml {

	@Test
	void testStart2CSV() {
		Csv2kml t = new Csv2kml();
		String s = "";
		try {
			s = ""+t.start2CSV(new Project(new Pdata()));
		}
		catch(Exception e) {
			fail("Should not get Exception!");
		}
		
		assertNotNull(s);
	}

}
