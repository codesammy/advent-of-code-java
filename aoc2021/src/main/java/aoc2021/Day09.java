package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2021.day08.EntryParser;
import aoc2021.day08.SignalPatternDecoder;
import aoc2021.day09.HeightMap;
import aoc2021.day09.HeightMapParser;
import aoc2021.day09.HeightMapPoint;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day09 extends BaseDay {
	
	/**
	 * In the output values, how many times do digits 1, 4, 7, or 8 appear?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		HeightMapParser parser = new HeightMapParser();
		HeightMap map = parser.parse(lines);
		Set<HeightMapPoint> lowPoints = map.getLowPoints();
		return ""+lowPoints.stream().mapToInt(p -> p.getHeightValue() + 1).sum();
	}

	/**
	 * What do you get if you add up all of the output values?
	 */
	@Input
	//@Gold
	public String gold(List<String> lines) {
		List<List<Integer>> allOutputValues = solution(lines);
		return ""+(allOutputValues.stream()
				.mapToInt(x->Integer.parseInt(x.stream()
						.map(Object::toString)
						.collect(Collectors.joining())))
				.sum());
	}
	
	private List<List<Integer>> solution(List<String> lines) {
		List<List<Integer>> allOutputValues = new ArrayList<>();
		for (String line : lines) {
			EntryParser parser = new EntryParser();
			parser.parseLine(line);
			List<Set<Character>> uniqueSignalPatterns = parser.getUniqueSignalPatterns();
			List<Set<Character>> encodedOutputValue = parser.getEncodedOutputValue();
			SignalPatternDecoder decoder = new SignalPatternDecoder();
			decoder.train(uniqueSignalPatterns);
			List<Integer> outputValue = decoder.decode(encodedOutputValue);
			allOutputValues.add(outputValue);
		}
		return allOutputValues;
	}

}
