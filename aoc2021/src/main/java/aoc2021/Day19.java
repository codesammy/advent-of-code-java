package aoc2021;

import java.util.List;

import aoc2021.days.day18.SnailFishNumber;
import aoc2021.days.day19.BeaconMapResolver;
import aoc2021.days.day19.Point3D;
import aoc2021.days.day19.ScannerResult;
import aoc2021.days.day19.ScannerResultParser;
import aoc2021.helper.Combinations;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day19 extends BaseDay {
	
	/**
	 * Assemble the full map of beacons. How many beacons are there?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		ScannerResultParser parser = new ScannerResultParser();
		parser.parse(lines);
		List<ScannerResult> results = parser.getResults();
		BeaconMapResolver resolver = new BeaconMapResolver(results);
		resolver.resolve();
		return ""+resolver.getBeaconCount();
	}

	/**
	 * What is the largest Manhattan distance between any two scanners?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		ScannerResultParser parser = new ScannerResultParser();
		parser.parse(lines);
		List<ScannerResult> results = parser.getResults();
		BeaconMapResolver resolver = new BeaconMapResolver(results);
		resolver.resolve();
		return ""+resolver.getLargestScannerDistance();
	}

}
