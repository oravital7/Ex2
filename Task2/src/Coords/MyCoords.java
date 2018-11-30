package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {

	//Earth’s radius, sphere
	private final int R=6371*1000;//6378137;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		//Position, decimal degrees
		double lat = gps.x();
		double lon = gps.y();


		//offsets in meters
		double dn = local_vector_in_meter.x();
		double de = local_vector_in_meter.y();

		//Coordinate offsets in radians
		double dLat = dn/R;
		double dLon = de/(R*Math.cos(Math.PI*lat/180)); //de/(R*Cos(Pi*lat/180));


		//OffsetPosition, decimal degrees
		double latO = lat + dLat * 180/Math.PI;
		double lonO = lon + dLon * 180/Math.PI ;
		Point3D P=new Point3D(latO, lonO, gps.z()+local_vector_in_meter.z());//returns the new point
		return P;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// The math module contains a function 
		// named toRadians which converts from 
		// degrees to radians. 
		double lon1 = Math.toRadians(gps0.y()); 
		double lon2 = Math.toRadians(gps1.y()); 
		double lat1 = Math.toRadians(gps0.x()); 
		double lat2 = Math.toRadians(gps1.x()); 

		// Haversine formula  
		double dlon = lon2 - lon1;  
		double dlat = lat2 - lat1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) 
				+ Math.cos(lat1) * Math.cos(lat2) 
				* Math.pow(Math.sin(dlon / 2),2); 

		double c = 2 * Math.asin(Math.sqrt(a)); 

		// Radius of earth in kilometers. Use 3956  
		// for miles 
		//double r = 6371; 

		// calculate the result 
		return(c * R);
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double b = R + gps1.x(); //destination.altitude;
		double c = R + gps0.x(); //source altitude;

		double b2       = b*b;
		double c2       = c*c;
		double bc2      = 2*b*c;

		// Longitudinal calculations
		double alpha    = gps1.y() - gps0.y();
		// Conversion to radian
		alpha = alpha * Math.PI / 180;
		// Small-angle approximation
		double cos      = 1 - alpha*alpha/2; //Math.cos(alpha);
		// Use the law of cosines / Al Kashi theorem
		double x        = Math.sqrt(b2 + c2 - bc2*cos);

		// Repeat for latitudinal calculations
		alpha = gps1.x() - gps0.x();
		alpha = alpha * Math.PI / 180;
		cos = 1 - alpha*alpha/2; //Math.cos(alpha);
		double y   = Math.sqrt(b2 + c2 - bc2*cos);

		// Obtain vertical difference, too
		double z   = gps1.z() - gps0.z();
		Point3D P=new Point3D(x, y, z);//returns the new point
		return P;

	}
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double lon1 = Math.toRadians(gps0.y()); 
		double lon2 = Math.toRadians(gps1.y()); 
		double lat1 = Math.toRadians(gps0.x()); 
		double lat2 = Math.toRadians(gps1.x()); 
		double g = lon2 - lon1;

		double left = Math.sin(g)*Math.cos(lat2);
		double right = Math.cos(lat1)*Math.sin(lat2)-Math.sin(lat1)*Math.cos(lat2)*Math.cos(g);

		double	azimut = Math.atan2(left, right);

		azimut = Math.toDegrees(azimut);
		if(azimut<0) azimut+=360;
		double distance = distance3d(gps0,gps1);
		double dd = gps1.z() - gps0.z();
		double eleveation = Math.toDegrees(Math.asin(dd/distance));
		
		double arr[] = {azimut, distance,eleveation};
		return arr;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x()<=180 && p.x()>=180 & p.y()<=90 && p.y()>=-90 && p.z()>=-450) return true; 
		return false;
	}

}
