package File_format;

import java.util.Iterator;

import GIS.Edata;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Ldata;
import Geom.Point3D;

public class Csv2kml {
	private StringBuilder sb;
	
	public StringBuilder start2CSV(GIS_project p) {
		sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle>\n<Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\n</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle>\n");
		sb.append("<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\n");

		Iterator<GIS_layer> it = p.iterator();
		while(it.hasNext()) {
			GIS_layer current = it.next();
			start2CSV(current);
		}

		sb.append("</Document>\n</kml>");
		return sb;
	}
	
	private void start2CSV(GIS_layer layer) {
		Ldata ldata = (Ldata) layer.get_Meta_data();
		String name = ldata.getName();
		String start ="<Folder>\n<name>"+name+"</name>\n";
		sb.append(start);
		
		Iterator<GIS_element> it = layer.iterator();
		while(it.hasNext()) {
			GIS_element current = it.next();
			Edata data = (Edata) current.getData();
			Point3D point = (Point3D) current.getGeom();

			sb.append("<Placemark>\n");
			
			sb.append("<name><![CDATA["+data.getSSID()+"]]>");
			sb.append("</name>\n");
			sb.append("<description>");
			sb.append("<![CDATA[BSSID: <b>"+data.getMAC()+"</b><br/>AuthMode: <b>"+data.getAuthMode()+"</b><br/>Channel: <b>"+data.getChannel()+"</b><br/>RSSI: <b>");
			sb.append(data.getRSSI()+"</b><br/>Date: <b>"+data.getFirstSeen()+"</b>]]>");
			sb.append("</description><styleUrl>#"+data.getCOLOR()+"</styleUrl>");
			sb.append('\n');
			sb.append("<Point>\n<coordinates>"+point.y()+","+point.x()+"</coordinates>\n</Point>");
			sb.append('\n');	
			sb.append("<TimeStamp>\n");
			sb.append("<when>"+data.getTimeStamp()+"</when>\n");
			sb.append("</TimeStamp>");
			
			sb.append("</Placemark>");
			sb.append('\n');

		}
		String end="</Folder>\n";
		sb.append(end);
	}

}
