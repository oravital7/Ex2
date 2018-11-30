package GIS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import Geom.Point3D;

public class Pdata implements Meta_data {
	private String time;
	private long UTC;

	public Pdata() {
		currentTime();
	}

	@Override
	public long getUTC() {
		return UTC;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	private void currentTime() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(tz);
		time = df.format(new Date());
		UTC = Instant.parse(time).toEpochMilli();
	}

}
