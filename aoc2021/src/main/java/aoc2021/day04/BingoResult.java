package aoc2021.day04;

public class BingoResult {

	private BingoBoard bingoBoard;
	private int score;
	private int lastCalledNumberIndex;

	public BingoResult(BingoBoard bingoBoard, int score, int lastCalledNumberIndex) {
		this.bingoBoard = bingoBoard;
		this.score = score;
		this.lastCalledNumberIndex = lastCalledNumberIndex;
	}

	public Integer getScore() {
		return score;
	}

	public Integer getLastCalledNumberIndex() {
		return lastCalledNumberIndex;
	}

	@Override
	public String toString() {
		return "BingoResult [score=" + score + ", lastCalledNumberIndex=" + lastCalledNumberIndex + "]";
	}
	
}
