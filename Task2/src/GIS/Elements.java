package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Elements implements GIS_element {
	private Point3D point;
	private Edata data;

	public Elements(Point3D point, Edata data) {
		this.point = point;
		this.data = data;
	}
	
	@Override
	public Geom_element getGeom() {
		return point;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords calculate = new MyCoords();
		point = calculate.add(point, vec);
	}
		
	@Override
	public Meta_data getData() {
		return data;
	}

	public String toString() {
		return data.toString() +','+ point.x()+','+point.y()+','+point.z();
	}
}
