package aoc2021.days.day04;

public class BingoBoardNumber {

	private int rowIndex;
	private int colIndex;
	private Integer number;
	private boolean marked;

	public BingoBoardNumber(int rowIndex, int colIndex, Integer number) {
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean b) {
		marked = true;
	}

	@Override
	public String toString() {
		return "BingoBoardNumber [rowIndex=" + rowIndex + ", colIndex=" + colIndex + ", number=" + number + ", marked="
				+ marked + "]";
	}

}
