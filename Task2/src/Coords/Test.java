package Coords;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import File_format.MultiCSV;
import GIS.Edata;
import GIS.Elements;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Layer;
import GIS.Ldata;
import GIS.Project;
import Geom.Point3D;

public class Test {
	static Point3D vec;
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		//		Point3D P1 = new Point3D(32.103315,35.209039,670);
		Point3D P1 = new Point3D(0,180,0);


		vec = new Point3D(0,1000,0.0);
		MyCoords co = new MyCoords();


		
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date d = df.parse("2017-12-01 10:50:05");
//		System.out.println(dff.format(d));
//		TimeZone tz = TimeZone.getTimeZone("UTC");
//		df.setTimeZone(tz);
//		String nowAsISO = df.format(new Date());
		
//		long tt = Instant.parse( "2014-10-23T00:35:14.800Z" ).toEpochMilli();
//		long tt = Instant.parse(nowAsISO).toEpochMilli();

//		System.out.println(tt);
//		System.out.println(nowAsISO);
		
		

		
//		CsvReader  r = new CsvReader("WigleWifi_20171203085618.csv");
//		Csv2Elem g = new Csv2Elem(r);
//		Layer layer = g.MakeElements();
//		Iterator<GIS_element> it = layer.iterator();
//		GIS_element current = it.next();

		MultiCSV ok = new MultiCSV("D:\\New folder");
		ok.convert2kml();
		System.out.println();
//		ok.convert2kml();
//		System.out.println();
//		Project p =	ok.getP();
//		Iterator<GIS_layer> itt = p.iterator();
//		while(itt.hasNext()) {
//			Layer l = (Layer) itt.next();
//			Iterator<GIS_element> i = l.iterator();
//			Elements s = (Elements) i.next();
//			Edata ed = (Edata) s.getData();
//			
//			System.out.println(ed.getFirstSeen());
//			System.out.println(ed.getTimeStamp());
//			System.out.println(ed.getUTC());
//			
//			System.out.println();
//			Ldata ld = (Ldata) l.get_Meta_data();
//			System.out.println(ld.getUTC());
//		}

		
	}

}
