package aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc2021.days.day05.Point;
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
import aoc2021.days.day17.StyleThrowSimulator;
import aoc2021.days.day17.TargetArea;
import aoc2021.days.day17.TargetAreaParser;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day17 extends BaseDay {
	
	/**
	 * What is the highest y position it reaches on this trajectory?
	 * target area: x=144..178, y=-100..-76
	 */
	@Input
	//@Silver
	public String silver(List<String> lines) {
		TargetAreaParser parser = new TargetAreaParser();
		parser.parse(lines);
		TargetArea target = parser.getTargetArea();
		Point probePosition = new Point(0, 0);
		StyleThrowSimulator sim = new StyleThrowSimulator(probePosition, target);
		sim.findMaxHittingHeight();
		return ""+sim.getMaxHittingHeight();
	}

	/**
	 * How many distinct initial velocity values cause the probe to be within the target area after any step?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		TargetAreaParser parser = new TargetAreaParser();
		parser.parse(lines);
		TargetArea target = parser.getTargetArea();
		Point probePosition = new Point(0, 0);
		StyleThrowSimulator sim = new StyleThrowSimulator(probePosition, target);
		sim.findMaxHittingHeight();
		return ""+sim.getDistinctHittingVelocityCount();
	}

}
