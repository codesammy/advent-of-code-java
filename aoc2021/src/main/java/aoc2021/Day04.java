package aoc2021;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import aoc2021.day04.BingoBoard;
import aoc2021.day04.BingoParser;
import aoc2021.day04.BingoResult;
import aoc2021.day04.BingoSimulator;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day04 extends BaseDay {
	
	/**
	 * What will your final score be if you choose that board?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		BiFunction<BingoResult, BingoResult, Boolean> compareResultComparator = (var a, var b) -> a.getLastCalledNumberIndex() < b.getLastCalledNumberIndex();
		return solution(lines, compareResultComparator).toString();
	}
	
	/**
	 * Figure out which board will win last. Once it wins, what would its final score be?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		BiFunction<BingoResult, BingoResult, Boolean> compareResultComparator = (var a, var b) -> a.getLastCalledNumberIndex() > b.getLastCalledNumberIndex();
		return solution(lines, compareResultComparator).toString();
	}
	
	private Integer solution(List<String> lines, BiFunction<BingoResult, BingoResult, Boolean> compareResultStrategy) {
		BingoParser parser = new BingoParser();
		parser.parse(lines);
		
		List<Integer> randomOrder = parser.getRandomOrder();
		List<BingoBoard> bingoBoards = parser.getBingoBoards();
		
		BingoResult wantedBingoResult = null;
		for (BingoBoard bingoBoard : bingoBoards/*.subList(bingoBoards.size()-1, bingoBoards.size())*/) {
			BingoSimulator bingoSimulator = new BingoSimulator(randomOrder, bingoBoard);

			if (bingoSimulator.canWinConditionBeReached()) {
				Set<Integer> unMarkedNumbers = bingoSimulator.getUnmarkedNumbers();
				int sumOfAllUnmarkedNumbers = unMarkedNumbers.stream().mapToInt(x->x).sum();
				int lastCalledNumber = bingoSimulator.getLastCalledNumber();
				int lastCalledNumberIndex = bingoSimulator.getLastCalledNumberIndex();
				int score = sumOfAllUnmarkedNumbers * lastCalledNumber;
				BingoResult currentSimResult = new BingoResult(bingoBoard, score, lastCalledNumberIndex);
				
				if (wantedBingoResult == null || compareResultStrategy.apply(currentSimResult, wantedBingoResult)) {
					wantedBingoResult = currentSimResult;
				}
			}
		}
		return wantedBingoResult.getScore();
	}

}
