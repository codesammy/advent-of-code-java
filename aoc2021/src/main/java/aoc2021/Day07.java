package aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day07 extends BaseDay {
	
	/**
	 * How many lanternfish would there be after 80 days?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		return solution(lines, x->Long.valueOf(x));
	}

	/**
	 * How much fuel must they spend to align to that position?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		return solution(lines, x->(long)IntStream.range(1, x+1).sum());
	}
	
	private String solution(List<String> lines, Function<Integer, Long> fuelCostForDistance) {
		List<Integer> submarinePositions = Arrays.stream(lines.get(0).split(",")).map(x->Integer.valueOf(x)).collect(Collectors.toList());
		int maxSubmarinePosition = submarinePositions.stream().mapToInt(x->x).max().getAsInt();
		long minFuelCost = Long.MAX_VALUE;
		for (int possiblePosition = 0; possiblePosition <= maxSubmarinePosition; possiblePosition++) {
			long totalFuelCost = 0;
			for (Integer submarinePosition : submarinePositions) {
				Integer distance = Math.abs(possiblePosition - submarinePosition);
				long cost = fuelCostForDistance.apply(distance);
				totalFuelCost += cost;
			}
			if (totalFuelCost < minFuelCost) {
				minFuelCost = totalFuelCost;
			}
		}
		
		return ""+minFuelCost;
	}

}
