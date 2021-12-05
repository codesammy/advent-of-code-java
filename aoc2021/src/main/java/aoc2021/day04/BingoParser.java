package aoc2021.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BingoParser {

	private List<Integer> randomOrder;
	private List<BingoBoard> bingoBoards;

	public void parse(List<String> lines) {

		String delimitedNumbers = lines.get(0);
		String[] splittedNumbers = delimitedNumbers.split(",");
		this.randomOrder = Arrays.asList(splittedNumbers).stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		
		int bingoBoardSize = 5;
		int bingoBoardCount = (lines.size() - 1) / (bingoBoardSize + 1);
		bingoBoards = new ArrayList<>();
		for (int i=0; i < bingoBoardCount; i++) {
			List<Integer> allNumbers = new ArrayList<>();
			int fromIndex = 2 + (i * (bingoBoardSize + 1));
			List<String> bingoBoardLines = lines.subList(fromIndex, fromIndex + bingoBoardSize);
			for (String bingoBoardLine : bingoBoardLines) {
				String[] splittedBingoBoardNumbers = bingoBoardLine.split(" +");
				List<Integer> bingoBoardNumbers = Arrays.asList(splittedBingoBoardNumbers).stream()
							.filter(x->!x.isEmpty())
							.map(Integer::parseInt)
							.collect(Collectors.toList());
				allNumbers.addAll(bingoBoardNumbers);
			}
			BingoBoard board = new BingoBoard(bingoBoardSize, allNumbers);
			bingoBoards.add(board);
		}
	}

	public List<Integer> getRandomOrder() {
		return randomOrder;
	}

	public List<BingoBoard> getBingoBoards() {
		return bingoBoards;
	}

}
