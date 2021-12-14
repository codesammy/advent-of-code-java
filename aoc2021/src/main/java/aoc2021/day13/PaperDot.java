package aoc2021.day13;

public class PaperDot {
	private boolean empty;

	public PaperDot(boolean empty) {
		this.empty = empty;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	@Override
	public String toString() {
		return isEmpty() ? "." : "#";
	}
}
