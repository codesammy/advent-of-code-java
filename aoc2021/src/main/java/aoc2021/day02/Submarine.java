package aoc2021.day02;

public class Submarine {
	private int horizontalPosition = 0;
	private int depth = 0;
	private int aim = 0;
	/**
	 * @return the horizontalPosition
	 */
	public int getHorizontalPosition() {
		return horizontalPosition;
	}
	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}
	public void moveForward(Integer amount) {
		this.horizontalPosition += amount;
	}
	public void moveDown(Integer amount) {
		this.depth += amount;
	}
	public void moveUp(Integer amount) {
		this.depth -= amount;
	}
	public void aimDown(Integer amount) {
		this.aim += amount;
	}
	public void aimUp(Integer amount) {
		this.aim -= amount;
	}
	public void aimForward(Integer amount) {
		this.moveForward(amount);
		this.moveDown(this.aim * amount);
	}
}