package aoc2021.days.day15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2021.days.day05.Point;

public class Cavern {
	private int width;
	private int height;
	Map<Point, Integer> riskLevels;
	
	public Cavern() {
		riskLevels = new HashMap<>();
	}

	public void add(Point p, Integer riskLevel) {
		riskLevels.put(p, riskLevel);
		width = Math.max(width, p.getX()+1);
		height = Math.max(height, p.getY()+1);
	}

	public Integer getTotalRiskLevel(List<Point> points) {
		return points.stream()
				.mapToInt(p-> riskLevels.get(p))
				.sum();
	}

	public Point getEndPoint() {
		return new Point(width-1, height-1);
	}

	public Set<Point> getNeighbors(Point point) {
		Set<Point> neighbors = new HashSet<>(Set.of(
				//new Point(-1, -1),
				new Point(-1,  0),
				//new Point(-1,  1),
				new Point( 0, -1),
				//new Point(0, 0),
				new Point( 0,  1),
				//new Point( 1, -1),
				new Point( 1,  0)
				//new Point( 1,  1)
				));
		if (point.getX() == 0) {
			neighbors.removeIf(p -> p.getX() < 0);
		}
		if (point.getY() == 0) {
			neighbors.removeIf(p -> p.getY() < 0);
		}
		if (point.getX()+1 == width) {
			neighbors.removeIf(p -> p.getX() > 0);
		}
		if (point.getY()+1 == height) {
			neighbors.removeIf(p -> p.getY() > 0);
		}
		return neighbors.stream()
				.map(p -> new Point(point.getX() + p.getX(), point.getY() + p.getY()))
				.collect(Collectors.toSet());
	}

	public Set<Point> getPoints() {
		return riskLevels.keySet();
	}

	public Cavern extend(int amount) {
		Cavern cavern = new Cavern();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int xMod = 0; xMod < amount; xMod++) {
					for (int yMod = 0; yMod < amount; yMod++) {
						Point p = new Point(x, y);
						Point pPrime = new Point(x + xMod * width, y + yMod * height);
						int levelMod = xMod + yMod;
						int riskLevel = (riskLevels.get(p) -1 + levelMod) % 9 + 1;
						if (x == 0 && (y == 5)) {
							System.out.println(pPrime + " mit " + riskLevel);
						}
						cavern.add(pPrime, riskLevel);
					}
				}
			}
		}
		return cavern;
	}

	public String render() {
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Point p = new Point(x, y);
				int level = riskLevels.get(p);
				sb.append(level);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Integer distance(Point a, Point b) {
		return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
	}

	public Integer getTotalRiskLevel(Point neighbor) {
		return riskLevels.get(neighbor);
	}

}
