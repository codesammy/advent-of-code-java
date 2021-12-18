package aoc2021;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import aoc2021.days.day05.LineSegment;
import aoc2021.days.day05.LineSegmentParser;
import aoc2021.days.day05.Point;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day05 extends BaseDay {
	
	/**
	 * Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		return solution(lines, lineSegment -> !lineSegment.isHorizontal() && !lineSegment.isVertical());
	}

	/**
	 * Consider all of the lines. At how many points do at least two lines overlap?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		return solution(lines, lineSegment -> false);
	}
	
	private String solution(List<String> lines, Predicate<LineSegment> skipWhenLineSegment) {
		LineSegmentParser lineSegmentParser = new LineSegmentParser();
		lineSegmentParser.parseLines(lines);
		Set<Point> allPoints = new HashSet<>();
		Set<Point> overlappingPoints = new HashSet<>();
		for (LineSegment lineSegment : lineSegmentParser.getLineSegments()) {
			if (skipWhenLineSegment.test(lineSegment)) {
				continue;
			}
			Set<Point> lineSegmentPoints = lineSegment.getAllPoints();
			for (Point lineSegmentPoint : lineSegmentPoints) {
				if (allPoints.contains(lineSegmentPoint)) {
					overlappingPoints.add(lineSegmentPoint);
				}
			}
			allPoints.addAll(lineSegmentPoints);
		}
		return ""+overlappingPoints.size();
	}

}
