package aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.IntList;
import aoc2021.meta.Silver;

public class Day01 extends BaseDay {

	/**
	 * Find the two entries that sum to 2020; what do you get if you multiply them together?
	 */
	@Input
	@Silver
	@IntList
	public String silver(List<Integer> lines) {
		return solution(lines, 2);
	}
	
	/**
	 * In your expense report, what is the product of the three entries that sum to 2020?
	 */
	@Input
	@Gold
	@IntList
	public String gold(List<Integer> lines) {
		return solution(lines, 3);
	}
	
	private String solution(List<Integer> lines, int numberOfElementsPerCombination) {
		for (List<Integer> combination : new Combinations<Integer>(lines, numberOfElementsPerCombination)) {
			int sum = combination.stream().mapToInt(x -> x).sum();
			if (sum == 2020) {
				return combination.stream().reduce(1, (var result, var element) -> result * element).toString();
			}
		}
		throw new IllegalStateException("found no solution");
	}
}
