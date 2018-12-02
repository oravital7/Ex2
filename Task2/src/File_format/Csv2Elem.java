package File_format;

import java.io.File;
import java.util.ArrayList;

import GIS.Edata;
import GIS.Elements;
import GIS.Layer;
import GIS.Ldata;
import Geom.Point3D;


/**
 * 
 * This class convert CSV data to java objects 
 *
 */
public class Csv2Elem {
	private int rows;
	private Layer layer;
	private ArrayList<ArrayList<String>> arr;

	public Csv2Elem(File entry) {
		CsvReader  r = new CsvReader(entry.toString()); // Call to CsvReader class with correct File Path 
		if(!r.checkCsv()) { // Check if is a valid file of our CSV
			throw new RuntimeException("File: "+entry.getPath() +" is Invalid! Please check the file");
		}
		rows = r.get_rows();
		arr = r.get_Array();
		int name = entry.getName().indexOf('.'); 
		Ldata ldata = new Ldata(entry.toString(),entry.getName().substring(0, name)); // Create data for layer
		layer = new Layer(ldata);
	}

	/**
	 * Running on the String array that we got from CsvReader class and 
	 * finally add it to the layer array
	 * 
	 * @return
	 */
	public Layer MakeElements() {
		for(int i=0; i<rows-1; i++) {
			Elements element = MakeElementsRaw(arr.get(i)); // Send to MakeElement function to create element object of the data
			layer.add(element); // add the element we created
		}
		return layer;
	}

	/**
	 * Putting in the appropriate places the data that we receive
	 * from the String array
	 * finally return element
	 * @param arr
	 * @return
	 */
	private Elements MakeElementsRaw(ArrayList<String> arr) {
		Edata data = new Edata();
		Point3D point = new Point3D(convert2Double(arr.get(6)) ,convert2Double(arr.get(7)) ,convert2Double(arr.get(8)));
		data.setMAC(arr.get(0)); 
		data.setSSID(arr.get(1));
		data.setAuthMode(arr.get(2));
		data.setFirstSeen(arr.get(3));
		data.setChannel(convert2Int(arr.get(4))); 
		data.setRSSI(convert2Int(arr.get(5))); 
		data.setAccuracyMeters(convert2Double(arr.get(9)));
		data.setTypes(arr.get(10));
		data.setCOLOR(color(data.getRSSI()));

		return new Elements(point,data);
	}

	/**
	 * Parse to int
	 * @param s
	 * @return
	 */
	private int convert2Int(String s) {
		int x = Integer.parseInt(s);
		return x;
	}

	/**
	 * Parse to double
	 * @param s
	 * @return
	 */
	private double convert2Double(String s) {
		double x = Double.parseDouble(s);
		return x;
	}

	/**
	 * Get RSSI variable and send the correct color
	 * accord the value
	 * @param RSSI
	 * @return
	 */
	private String color(int RSSI) {
		if(RSSI > -75) return "green";
		else if(RSSI > -90) return "yellow";
		return "red";
	}

}
