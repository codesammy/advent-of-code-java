package aoc2021.days.day05;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LineSegmentParser {
	
	Set<LineSegment> lineSegments;

	public void parseLines(List<String> lines) {
		lineSegments = new HashSet<>();
		for (String line : lines) {
			LineSegment lineSegment = parseLine(line);
			lineSegments.add(lineSegment);
		}
	}

	private LineSegment parseLine(String line) {
		LineSegment lineSegment = new LineSegment();
		String[] points = line.split(" -> ");
		Point p1 = parsePoint(points[0]);
		lineSegment.setP1(p1);
		Point p2 = parsePoint(points[1]);
		lineSegment.setP2(p2);
		return lineSegment;
	}

	private Point parsePoint(String point) {
		String[] pair = point.split(",");
		Integer x = parseComponent(pair[0]);
		Integer y = parseComponent(pair[1]);
		Point p = new Point(x, y);
		return p;
	}

	private Integer parseComponent(String component) {
		return Integer.parseInt(component);
	}

	public Set<LineSegment> getLineSegments() {
		return lineSegments;
	}

}
