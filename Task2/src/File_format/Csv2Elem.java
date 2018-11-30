package File_format;

import java.io.File;
import java.util.ArrayList;

import GIS.Edata;
import GIS.Elements;
import GIS.Layer;
import GIS.Ldata;
import Geom.Point3D;

public class Csv2Elem {
	private int rows;
	private Layer layer;
	private ArrayList<ArrayList<String>> arr;

	public Csv2Elem(File entry) {
		CsvReader  r = new CsvReader(entry.toString());
		if(!r.checkCsv()) {
			throw new RuntimeException("File: "+entry.getPath() +" is Invaild");
		}
		rows = r.get_rows();
		arr = r.get_Array();
		int name = entry.getName().indexOf('.');
		Ldata ldata = new Ldata(entry.toString(),entry.getName().substring(0, name));
		layer = new Layer(ldata);
	}

	public Layer MakeElements() {
		for(int i=1; i<rows-1; i++) {
			Elements element = MakeElementsRaw(arr.get(i));
			layer.add(element);
		}
		return layer;
	}

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

	private int convert2Int(String s) {
		int x = Integer.parseInt(s);
		return x;
	}

	private double convert2Double(String s) {
		double x = Double.parseDouble(s);
		return x;
	}

	private String color(int RSSI) {
		if(RSSI > -75) return "green";
		else if(RSSI > -90) return "yellow";
		return "red";
	}

}
