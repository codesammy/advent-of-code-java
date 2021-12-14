package aoc2021.day13;

public class FoldInstruction {
	private int xAmount;
	private int yAmount;
	public FoldInstruction(int xAmount, int yAmount) {
		this.xAmount = xAmount;
		this.yAmount = yAmount;
	}
	public int getXAmount() {
		return xAmount;
	}
	public int getYAmount() {
		return yAmount;
	}
}
