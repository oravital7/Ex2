package GIS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;
/**
 * 
 * @author Dana Mor and Or avital
 *this class represent the Data of the layer
 */
public class Ldata implements Meta_data {

	private String dir, name, time;
	private long UTC;
	/**
	 * 
	 * @param dir - path of the file
	 * @param name - name of the file
	 */
	public Ldata(String dir,String name) {
		this.dir=dir;
		this.name=name;
		currentTime();
	}
	/**
	 * this function saves the time when the layer was written
	 */
	private void currentTime() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		time = df.format(new Date());
		UTC = Instant.parse(time).toEpochMilli();
	}

	public String getName() {
		return name;
	}

	public String getTime() {
		return time;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * this function returns UTC
	 */
	@Override
	public long getUTC() {
		return UTC;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return dir+","+name+","+UTC+","+time;
	}

}
