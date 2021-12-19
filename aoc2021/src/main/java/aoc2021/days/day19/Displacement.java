package aoc2021.days.day19;

public class Displacement {
	private Point3D position;
	private int orientation;
	public Displacement(Point3D position, int orientation) {
		this.position = position;
		this.orientation = orientation;
	}
	public Point3D getPosition() {
		return position;
	}
	public int getOrientation() {
		return orientation;
	}
}
