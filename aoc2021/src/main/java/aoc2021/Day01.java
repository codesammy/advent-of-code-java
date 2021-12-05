package aoc2021;

import java.util.List;

import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.IntList;
import aoc2021.meta.Silver;

public class Day01 extends BaseDay {

	/**
	 * How many measurements are larger than the previous measurement?
	 */
	@Input
	@Silver
	@IntList
	public String silver(List<Integer> lines) {
		return solution(lines, 1);
	}
	
	/**
	 * How many sums are larger than the previous sum?
	 */
	@Input
	@Gold
	@IntList
	public String gold(List<Integer> lines) {
		return solution(lines, 3);
	}
	
	private String solution(List<Integer> lines, int windowSize) {
		Integer increasements = 0;
		int last = Integer.MAX_VALUE;
		for (int i=0; i < lines.size() - windowSize + 1; i++) {
			Integer depth = lines.subList(i, i+windowSize).stream().mapToInt(x->x).sum();
			if (depth > last) {
				increasements++;
			}
			last = depth;
		}
		return increasements.toString();
	}
}
