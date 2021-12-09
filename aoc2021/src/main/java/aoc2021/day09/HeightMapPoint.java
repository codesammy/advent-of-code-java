package aoc2021.day09;

public class HeightMapPoint {
	private int x;
	private int y;
	private int heightValue;
	
	public HeightMapPoint(int x, int y, int heightValue) {
		this.x = x;
		this.y = y;
		this.heightValue = heightValue;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeightValue() {
		return heightValue;
	}
}
