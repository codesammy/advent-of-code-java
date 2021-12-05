package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day03 extends BaseDay {
	
	/**
	 * What is the power consumption of the submarine?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		int bitCount = lines.get(0).length();
		int[] gammaRates = new int[bitCount];
		for (String line : lines) {
			List<Integer> bits = line.chars().mapToObj(x->x-'0').collect(Collectors.toList());
			for (var i=0; i<bitCount; i++) {
				gammaRates[i] += bits.get(i) == 1 ? 1 : -1;
			}
		}
		String gammaRateBinary = Arrays.stream(gammaRates).mapToObj(x->x>0?"1":"0").collect(Collectors.joining());
		int gammaRate = Integer.parseInt(gammaRateBinary, 2);
		int oneForEveryBit = Double.valueOf((Math.pow(2, bitCount) - 1)).intValue();
		int epsilonRate = oneForEveryBit ^ gammaRate;
		return Integer.valueOf(gammaRate * epsilonRate).toString();
	}
	
	/**
	 * What is the life support rating of the submarine?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		int bitCount = lines.get(0).length();
		ArrayList<Integer> numbers = (ArrayList<Integer>) lines.stream()
				.map(x -> Integer.parseInt(x, 2))
				.collect(Collectors.toList());
		
		int oxygenGeneratorRating = filterBits((ArrayList<Integer>)numbers.clone(), bitCount, true, 1);
		int co2ScrubberRating = filterBits((ArrayList<Integer>)numbers.clone(), bitCount, false, 0);
		
		return "" + (oxygenGeneratorRating * co2ScrubberRating);
	}

	private int filterBits(ArrayList<Integer> numbers, int bitCount, boolean mostCommon, int whenCountEqualValue) {
		int pos = bitCount - 1;
		while (numbers.size() > 1 && pos >= 0) {
			int mostCommonBit = mostCommonBit(numbers, pos, mostCommon, whenCountEqualValue);
			final int posF = pos;
			numbers.removeIf(x -> getBitAtPos(x, posF) != mostCommonBit);
			pos--;
		}
		return numbers.get(0);
	}

	private int mostCommonBit(List<Integer> numbers, int pos, boolean mostCommon, int whenEqualCountValue) {
		Map<Integer, Long> bitCountGroupBy = numbers.stream().map(x -> getBitAtPos(x, pos)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Long zeroCount = bitCountGroupBy.get(0);
        Long oneCount = bitCountGroupBy.get(1);
        if (zeroCount > oneCount) {
        	return mostCommon ? 0 : 1;
        } else if (zeroCount < oneCount) {
        	return mostCommon ? 1 : 0;
        }
		return whenEqualCountValue;
	}
	
	private int getBitAtPos(Integer number, int pos) {
		return (Double.valueOf(Math.pow(2, pos)).intValue() & number) == 0 ? 0 : 1;
	}
}
