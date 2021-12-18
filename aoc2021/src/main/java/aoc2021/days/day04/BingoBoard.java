package aoc2021.days.day04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class BingoBoard {

	private int bingoBoardSize;
	private Map<Integer, Set<BingoBoardNumber>> rows = new HashMap<>();
	private Map<Integer, Set<BingoBoardNumber>> cols = new HashMap<>();
	
	private Map<Integer, Set<BingoBoardNumber>> diags = new HashMap<>();

	public BingoBoard(int bingoBoardSize, List<Integer> allNumbers) {
		this.bingoBoardSize = bingoBoardSize;
		for (int i = 0; i < allNumbers.size(); i++) {
			int rowIndex = i / this.bingoBoardSize;
			int colIndex = i % this.bingoBoardSize;
			BingoBoardNumber num = new BingoBoardNumber(rowIndex, colIndex, allNumbers.get(i));
			rows.putIfAbsent(rowIndex, new HashSet<>());
			rows.get(rowIndex).add(num);
			cols.putIfAbsent(colIndex, new HashSet<>());
			cols.get(colIndex).add(num);
			if (rowIndex == colIndex) {
				diags.putIfAbsent(0, new HashSet<>());
				diags.get(0).add(num);
			}
			if (rowIndex + colIndex == this.bingoBoardSize - 1) {
				diags.putIfAbsent(1, new HashSet<>());
				diags.get(1).add(num);
			}
		}
	}

	public BingoBoard() {
	}

	public Set<BingoBoardNumber> getAllNumbers() {
		Set<BingoBoardNumber> allNumbers = rows.values().stream().flatMap(x->x.stream()).collect(Collectors.toSet());
		return allNumbers;
	}

	public boolean isWinConditionReached() {
		Set<Set<BingoBoardNumber>> allLines = new HashSet<>();
		allLines.addAll(rows.values());
		allLines.addAll(cols.values());
		//allLines.addAll(diags.values());
		boolean winCondition = allLines.stream().anyMatch(x -> x.stream().allMatch(num -> num.isMarked()));
		return winCondition;
	}

}
