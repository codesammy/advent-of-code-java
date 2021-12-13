package aoc2021;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc2021.day10.ChunkParser;
import aoc2021.day11.DumboOcatopusGridParser;
import aoc2021.day11.DumboOcatopusGridSimulator;
import aoc2021.day11.DumboOcotopusGrid;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day11 extends BaseDay {
	
	/**
	 * Given the starting energy levels of the dumbo octopuses in your cavern, simulate 100 steps.
	 * How many total flashes are there after 100 steps?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		DumboOcatopusGridParser parser = new DumboOcatopusGridParser();
		DumboOcotopusGrid grid = parser.parse(lines);
		DumboOcatopusGridSimulator sim = new DumboOcatopusGridSimulator(grid);
		sim.advanceNSteps(100);

		return ""+sim.getFlashCount();
	}

	/**
	 * What is the first step during which all octopuses flash?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		DumboOcatopusGridParser parser = new DumboOcatopusGridParser();
		DumboOcotopusGrid grid = parser.parse(lines);
		DumboOcatopusGridSimulator sim = new DumboOcatopusGridSimulator(grid);
		sim.advanceUntilSynchronized();

		return ""+sim.getStepCount();
	}

}
