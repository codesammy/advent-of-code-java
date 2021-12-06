package aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day06 extends BaseDay {
	
	/**
	 * How many lanternfish would there be after 80 days?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		return solution(lines, 80);
	}

	/**
	 * How many lanternfish would there be after 256 days?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		return solution(lines, 256);
	}
	
	private String solution(List<String> lines, int daysToSimulate) {
		List<Integer> lanternFishes = Arrays.stream(lines.get(0).split(",")).map(x->Integer.valueOf(x)).collect(Collectors.toList());
		final int maxDaysUntilReproducing = 9;
		long[] daysUntilReproducingCounted = new long[maxDaysUntilReproducing];
		for (Integer lanternFish : lanternFishes) {
			daysUntilReproducingCounted[lanternFish]++;
		}
		for (int day = 0; day < daysToSimulate; day++) {
			long newLanternFishes = 0;
			for (int daysUntilReproducing = 0; daysUntilReproducing < maxDaysUntilReproducing; daysUntilReproducing++) {
				if (daysUntilReproducing == 0) {
					newLanternFishes = daysUntilReproducingCounted[daysUntilReproducing];
				} else {
					daysUntilReproducingCounted[daysUntilReproducing-1] += daysUntilReproducingCounted[daysUntilReproducing];
				}
				daysUntilReproducingCounted[daysUntilReproducing] = 0;
			}
			daysUntilReproducingCounted[maxDaysUntilReproducing-3] += newLanternFishes;
			daysUntilReproducingCounted[maxDaysUntilReproducing-1] += newLanternFishes;
		}
		return ""+Arrays.stream(daysUntilReproducingCounted).sum();
	}

}
