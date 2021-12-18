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
import aoc2021.days.day15.Cavern;
import aoc2021.days.day15.CavernParser;
import aoc2021.days.day15.CavernPath;
import aoc2021.days.day15.CavernPathFinder;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day15 extends BaseDay {
	
	/**
	 * What is the sum of the risk levels of all low points on your heightmap?
	 */
	@Input
	//@Silver
	public String silver(List<String> lines) {
		CavernParser parser = new CavernParser();
		Cavern cavern = parser.parse(lines);
		CavernPathFinder pathFinder = new CavernPathFinder(cavern);
		CavernPath path = pathFinder.findLowestRiskPath();
		return ""+path.getTotalRisk();
	}

	/**
	 * Using the full map, what is the lowest total risk of any path from the top left to the bottom right?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		CavernParser parser = new CavernParser();
		Cavern cavern = parser.parse(lines);
		cavern = cavern.extend(5);
		System.out.println(cavern.render());
		CavernPathFinder pathFinder = new CavernPathFinder(cavern);
		CavernPath path = pathFinder.findLowestRiskPath();
		return ""+path.getTotalRisk();
	}

}
