package aoc2021.days.day04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BingoSimulator {

	private List<Integer> randomOrder;
	private BingoBoard bingoBoard;
	
	private int lastCalledNumberIndex;

	public BingoSimulator(List<Integer> randomOrder, BingoBoard bingoBoard) {
		this.randomOrder = randomOrder;
		this.bingoBoard = bingoBoard;
	}

	public int getLastCalledNumber() {
		return randomOrder.get(lastCalledNumberIndex);
	}

	public Set<Integer> getUnmarkedNumbers() {
		Set<BingoBoardNumber> allNumbers = bingoBoard.getAllNumbers();
		Set<Integer> unmarkedNumbers = allNumbers.stream()
		.filter(x->!x.isMarked())
		.map(x->x.getNumber())
		.collect(Collectors.toSet());
		return unmarkedNumbers;
	}

	public boolean canWinConditionBeReached() {
		for (lastCalledNumberIndex = 0; lastCalledNumberIndex < this.randomOrder.size(); lastCalledNumberIndex++) {
			Integer drawnNumber = this.randomOrder.get(lastCalledNumberIndex);
			bingoBoard.getAllNumbers().stream()
			.filter(x -> x.getNumber() == drawnNumber)
			.forEach(num -> num.setMarked(true));
			if (this.bingoBoard.isWinConditionReached()) {
				return true;
			}
		}
		return false;
	}

	public int getLastCalledNumberIndex() {
		return lastCalledNumberIndex;
	}

}
