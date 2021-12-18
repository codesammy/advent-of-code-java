package aoc2021.days.day17;

import aoc2021.days.day05.Point;

public class TargetArea {
	private Point topLeft;
	private Point bottomRight;

	public TargetArea(Point topLeft, Point bottomRight) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;

	}

	public Integer getHighestX() {
		return bottomRight.getX();
	}

	public Integer getLowestY() {
		return bottomRight.getY();
	}

	public boolean contains(Point position) {
		// TODO Auto-generated method stub
		return position.getX() >= topLeft.getX()
				&& position.getX() <= bottomRight.getX()
				&& position.getY() <= topLeft.getY()
				&& position.getY() >= bottomRight.getY();
	}

	public int getHighestY() {
		return topLeft.getX();
	}

	@Override
	public String toString() {
		return "TargetArea [topLeft=" + topLeft + ", bottomRight=" + bottomRight + "]";
	}
	
	
}