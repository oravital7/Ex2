package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

import File_format.Csv2Elem;
import GIS.Elements;
import GIS.GIS_element;
import GIS.Layer;
import GIS.Ldata;

class TestCsv2Elem {
	Layer l;
	@Test
	void testCsv2Elem() {

		//Test invalid file
		File f = new File("ExamplesFiles\\empty.csv");
		try {
			Csv2Elem c = new Csv2Elem(f);
			fail("Should get exception invaild file!");

		}
		catch(RuntimeException e) {
			assertTrue(true);
		}

		// Test valid file
		f = new File("ExamplesFiles\\Good\\WigleWifi_20171201110209.csv");
		try {
			Csv2Elem c = new Csv2Elem(f);
		}
		catch(Exception e ) {
			fail("Should not get exception!");
		}

	}

	@Test
	void testMakeElements() {
		// Test create elements from good CSV
		File f = new File("ExamplesFiles\\Good\\WigleWifi_20171201110209.csv");
		Csv2Elem c = new Csv2Elem(f);
		try {
			l = c.MakeElements();
		}
		catch(Exception e) {
			fail("Should not get exception!");
		}
		
		// Test correct data
		assertEquals("WigleWifi_20171201110209", l.toString());
		Ldata ldata = (Ldata) l.get_Meta_data();
		assertEquals("ExamplesFiles\\Good\\WigleWifi_20171201110209.csv", ldata.getDir());
		assertEquals(159, l.size());
		// Test each element in the array is not null
		for(GIS_element e : l) {
			assertNotNull(e);
		}
		
	}

}
