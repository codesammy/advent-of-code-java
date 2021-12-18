package aoc2021.days.day05;

import java.util.HashSet;
import java.util.Set;

public class LineSegment {
	private Point p1;
	private Point p2;
	public Point getP1() {
		return p1;
	}
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	public Point getP2() {
		return p2;
	}
	public void setP2(Point p2) {
		this.p2 = p2;
	}
	@Override
	public String toString() {
		return "LineSegment [p1=" + p1 + ", p2=" + p2 + "]";
	}
	public Set<Point> getAllPoints() {
		Set<Point> allPoints = new HashSet<>();
		
		int slopeX = p2.getX() - p1.getX();
		int slopeY = p2.getY() - p1.getY();
		int incX = slopeX == 0 ? 0 : slopeX / Math.abs(slopeX);
		int incY = slopeY == 0 ? 0 : slopeY / Math.abs(slopeY);
		for (int x = p1.getX(), y = p1.getY(); x != p2.getX() || y != p2.getY(); x = x + incX, y = y + incY) {
			allPoints.add(new Point(x, y));
		}
		allPoints.add(p2);
		
		return allPoints;
	}
	public boolean isHorizontal() {
		return p1.getY().equals(p2.getY());
	}
	public boolean isVertical() {
		return p1.getX().equals(p2.getX());
	}
	
}
