package aoc2021;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc2021.days.day10.ChunkParser;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day10 extends BaseDay {
	
	/**
	 * Find the first illegal character in each corrupted line of the navigation subsystem.
	 * What is the total syntax error score for those errors?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		ChunkParser parser = new ChunkParser();
		parser.parse(lines);
		
		Map<Character, Long> corruptionScoreTable = Map.of(')', 3L, ']', 57L, '}', 1197L, '>', 25137L);
		long corruptionScore = parser.getFirstIllegalCharacters().stream()
				.mapToLong(x -> corruptionScoreTable.get(x))
				.sum();
		return ""+corruptionScore;
	}

	/**
	 * Find the completion string for each incomplete line, score the completion strings, and sort the scores.
	 * What is the middle score?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		ChunkParser parser = new ChunkParser();
		parser.parse(lines);
		
		Map<Character, Long> autocompletionScoreTable = Map.of(')', 1L, ']', 2L, '}', 3L, '>', 4L);
		List<Long> autocompletionScores = parser.getAutoCompletedClosingChunkChars().stream()
				.map(x -> x.chars()
						.mapToLong(i->i)
						.reduce(0, (total, c) -> {
							System.out.println("score of " + x + " is " + (total*5 + autocompletionScoreTable.get(Character.valueOf((char) c))));
							return total*5 + autocompletionScoreTable.get(Character.valueOf((char) c));
						}))
				.sorted()
				.collect(Collectors.toList());
		long autocompletionTotalScore = autocompletionScores.get(autocompletionScores.size()/2);
		return ""+autocompletionTotalScore;
	}

}
