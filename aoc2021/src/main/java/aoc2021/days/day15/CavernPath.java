package aoc2021.days.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import aoc2021.days.day05.Point;

public class CavernPath {
	private Cavern cavern;
	private List<Point> points;
	
	public CavernPath(Cavern cavern, List<Point> points) {
		this.cavern = cavern;
		this.points = new ArrayList<>(points);
	}

	public Integer getTotalRisk() {
		return cavern.getTotalRiskLevel(this.points);
	}

	public Point getLastPoint() {
		return points.get(points.size()-1);
	}

	public CavernPath extend(Point appendPoint) {
		List<Point> newPoints = new ArrayList<>(this.points);
		newPoints.add(appendPoint);
		return new CavernPath(cavern, newPoints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cavern, points);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CavernPath other = (CavernPath) obj;
		return Objects.equals(cavern, other.cavern) && Objects.equals(points, other.points);
	}

	public boolean hasVisitedPointTwice() {
		return points.stream()
				.collect(Collectors.groupingBy(x->x, Collectors.counting())).values().stream()
				.anyMatch(c -> c > 1);
	}

	public boolean contains(Point p) {
		return points.contains(p);
	}
	
	@Override
	public String toString() {
		return points.stream().map(p -> p.toString() + cavern.getTotalRiskLevel(List.of(p)).toString()).collect(Collectors.joining(" -> "));
	}
}
