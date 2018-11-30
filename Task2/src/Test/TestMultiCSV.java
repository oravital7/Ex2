package Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import File_format.MultiCSV;
import GIS.GIS_layer;
import GIS.Project;

class TestMultiCSV {
	MultiCSV check;
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMultiCSV() {
		try {
		 check = new MultiCSV("ExamplesFiles\\Good");
		}
		catch(Exception e) {
			fail("Should not get exception!");
		}
		
		Project p = check.getP();
		assertEquals(2, p.size());
		
		for(GIS_layer l : p) {
			//First CSV = 185, second = 159
			assertTrue(159==l.size() || 185 == l.size());
		}
	}

	@Test
	void testConvert2kml() {
		check = new MultiCSV("ExamplesFiles\\Good");
		try {
			check.convert2kml();
		}
		catch(Exception e) {
			fail("Should not get exception!");
		}
		File f = new File("output.kml");
		// Check file exported
		assertTrue(f.exists());
	}

	@Test
	void testGetFolder() {
		 check = new MultiCSV("ExamplesFiles\\Good");
		assertEquals("ExamplesFiles\\Good", check.getFolder());
	}

}
