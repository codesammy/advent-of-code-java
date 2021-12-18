package aoc2021;

import java.util.List;

import aoc2021.days.day18.SnailFishNumber;
import aoc2021.helper.Combinations;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day18 extends BaseDay {
	
	/**
	 * Add up all of the snailfish numbers from the homework assignment in the order they appear.
	 * What is the magnitude of the final sum?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		SnailFishNumber num = new SnailFishNumber(lines.get(0));
		for (String line : lines.subList(1, lines.size())) {
			num = num.add(new SnailFishNumber(line));
		}
		
		return ""+num.calcMagnitude();
	}

	/**
	 * What is the largest magnitude of any sum of two different snailfish numbers from the homework assignment?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		Combinations<String> combinations = new Combinations<>(lines, 2);
		
		long maxMagnitude = 0;
		for (List<String> combo : combinations) {
			SnailFishNumber a = new SnailFishNumber(combo.get(0));
			SnailFishNumber b = new SnailFishNumber(combo.get(1));
			Long ab = a.add(b).calcMagnitude();
			Long ba = b.add(a).calcMagnitude();
			maxMagnitude = List.of(maxMagnitude, ab, ba).stream().mapToLong(x->x).max().getAsLong();
		}
		
		return ""+maxMagnitude;
	}

}
