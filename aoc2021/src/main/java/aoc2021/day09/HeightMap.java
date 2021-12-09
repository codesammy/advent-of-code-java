package aoc2021.day09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2021.day05.Point;

public class HeightMap {
	private Map<Point,HeightMapPoint> points;
	private List<HeightMapPoint> cols;
	
	public HeightMap() {
		points = new HashMap<>();
	}

	public void add(HeightMapPoint hmp) {
		Point p = new Point(hmp.getX(), hmp.getY());
		points.put(p, hmp);
	}

	public Set<HeightMapPoint> getLowPoints() {
		return points.values().stream()
		.filter(p -> getAdjacentPoints(p).stream().allMatch(o -> o.getHeightValue() > p.getHeightValue()))
		.collect(Collectors.toSet());
	}
	
	private Set<HeightMapPoint> getAdjacentPoints(HeightMapPoint hmp) {
		Set<HeightMapPoint> adjacentPoints = new HashSet<>();
		{
			Point p = new Point(hmp.getX()+1, hmp.getY());
			HeightMapPoint ap = points.get(p);
			if (ap != null)
				adjacentPoints.add(ap);
		}
		{
			Point p = new Point(hmp.getX()-1, hmp.getY());
			HeightMapPoint ap = points.get(p);
			if (ap != null)
				adjacentPoints.add(ap);
		}
		{
			Point p = new Point(hmp.getX(), hmp.getY()+1);
			HeightMapPoint ap = points.get(p);
			if (ap != null)
				adjacentPoints.add(ap);
		}
		{
			Point p = new Point(hmp.getX(), hmp.getY()-1);
			HeightMapPoint ap = points.get(p);
			if (ap != null)
				adjacentPoints.add(ap);
		}
		return adjacentPoints;
	}
}
