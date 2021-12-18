package aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc2021.days.day08.EntryParser;
import aoc2021.days.day08.SignalPatternDecoder;
import aoc2021.days.day09.HeightMap;
import aoc2021.days.day09.HeightMapBasin;
import aoc2021.days.day09.HeightMapParser;
import aoc2021.days.day09.HeightMapPoint;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day09 extends BaseDay {
	
	/**
	 * What is the sum of the risk levels of all low points on your heightmap?
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
	 * What do you get if you multiply together the sizes of the three largest basins?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		HeightMapParser parser = new HeightMapParser();
		HeightMap map = parser.parse(lines);
		Set<HeightMapBasin> lowPoints = map.getBasins();
		return ""+lowPoints.stream()
		.sorted((a,b) -> b.getSize().compareTo(a.getSize()))
		.collect(Collectors.toList())
		.subList(0, 3).stream()
		.mapToInt(p -> p.getSize())
		.reduce((a,b) -> a*b).getAsInt();
	}

}
