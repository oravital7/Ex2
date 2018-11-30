package GIS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;

public class Edata implements Meta_data {

	private String MAC, SSID, AuthMode, Type, COLOR, FirstSeen;
	private int Channel, RSSI;
	private double AccuracyMeters;
	private long UTC=0;
	private Date date;


	//*****************meta data functions**********************//
	@Override
	public long getUTC() {
		return UTC;
	}

	private void updateUTC() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		String time = df.format(date);
		UTC = Instant.parse(time).toEpochMilli();
	}

	//unnecessary at the moment
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	//*****************Public methods**********************//

	public String getTimeStamp() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String TimeStamp = df.format(date);
		return TimeStamp;
	}

	public void setFirstSeen(String FirstSeen) {
		this.FirstSeen = FirstSeen;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df.parse(FirstSeen);
		} catch (ParseException e) {
			throw new RuntimeException("Invaild Format Date");
		}
		updateUTC();
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}

	public void setTypes(String types) {
		Type = types;
	}

	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getMAC() {
		return MAC;
	}

	public String getSSID() {
		return SSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getCOLOR() {
		return COLOR;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}

	public double getAccuracyMeters() {
		return AccuracyMeters;
	}


	public void setChannel(int channel) {
		Channel = channel;
	}

	public void setRSSI(int rSSI) {
		RSSI = rSSI;
	}

	public void setAccuracyMeters(double accuracyMeters) {
		AccuracyMeters = accuracyMeters;
	}

	public String toString() {
		String s = MAC + "," + SSID + "," +AuthMode + "," + 
				FirstSeen + ","+Channel + "," + RSSI + "," +AccuracyMeters +","+ Type;
		return s;
	}
}
