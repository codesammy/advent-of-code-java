package aoc2021.days.day19;

public class Point3D {
	
	private int x;
	private int y;
	private int z;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "" + x + ", " + y + ", " + z;
	}

	public Point3D minus(Point3D other) {
		return new Point3D(x - other.getX(), y - other.getY(), z - other.getZ());
	}

	public Point3D turnLeft() {
		return new Point3D(y, -x, z);
	}

	public Point3D rollLeft() {
		return new Point3D(x, -z, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public Point3D add(Point3D other) {
		return new Point3D(x + other.getX(), y + other.getY(), z + other.getZ());
	}

	public int distance(Point3D other) {
		return Math.abs(other.x - x) + Math.abs(other.y - y) + Math.abs(other.z - z);
	}
	
}
