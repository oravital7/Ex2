package GIS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;
/**
 * This class represent the Data of the elements
 *
 */
public class Edata implements Meta_data {

	private String MAC, SSID, AuthMode, Type, COLOR, FirstSeen, Time;
	private int Channel, RSSI;
	private double AccuracyMeters;
	private long UTC=0;
	private Date date;


	//*****************meta data functions**********************//
	@Override
	public long getUTC() {
		return UTC;
	}

	/**
	 * Calculate the current UTC when creating the file
	 */
	private void updateUTC() {
		TimeZone tz = TimeZone.getTimeZone("UTC"); // Determine UTC to be The timezone
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Data format ISO-8601(google earth TimeStamp)
		df.setTimeZone(tz);
		Time = df.format(date); // Save TimeStamp as String
		UTC = Instant.parse(Time).toEpochMilli(); // and finally save long milisec for UTC
	}

	//unnecessary at the moment
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	//*****************Public methods**********************//


	public String getTimeStamp() {
		return Time;
	}

	/**
	 * When we got the FirstSeen we can calculate as well UTC
	 * and that function make a Date variable
	 * @param FirstSeen
	 */
	public void setFirstSeen(String FirstSeen) {
		this.FirstSeen = FirstSeen;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // FirstSeen as that format
		try {
			date = df.parse(FirstSeen); // Save it as a date
		} catch (ParseException e) {
			throw new RuntimeException("Invaild Format Date"); // If something go wrong with parse
		}
		updateUTC(); // Update UTC as well
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
		String s = MAC + ',' + SSID + ',' +AuthMode + ',' + 
				FirstSeen + ','+Channel + ',' + RSSI + ',' +AccuracyMeters +','+ Type;
		return s;
	}
}
