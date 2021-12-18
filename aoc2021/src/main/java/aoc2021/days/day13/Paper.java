package aoc2021.days.day13;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import aoc2021.days.day05.Point;

public class Paper {
	private Map<Point, PaperDot> dots;
	private int width;
	private int height;
	
	public Paper() {
		dots = new HashMap<>();
	}

	public void addDot(Point point, PaperDot paperDot) {
		dots.put(point, paperDot);
		if (point.getX()+1 > width) {
			width = point.getX()+1;
		}
		if (point.getY()+1 > height) {
			height = point.getY()+1;
		}
	}

	public long getDotCount() {
		return dots.values().stream()
				.filter(d -> !d.isEmpty())
				.count();
	}

	public Paper mirrorX(int xOffset) {
		Paper mirror = new Paper();
		for (Entry<Point, PaperDot> entry : dots.entrySet()) {
			Point point = entry.getKey();
			Point mirroredPoint = new Point(width - point.getX() - 1, point.getY());
			PaperDot dot = entry.getValue();
			if (mirroredPoint.getX() < xOffset) {
				mirror.addDot(mirroredPoint, dot);
			}
		}
		return mirror;
	}

	public Paper mirrorY(int yOffset) {
		Paper mirror = new Paper();
		for (Entry<Point, PaperDot> entry : dots.entrySet()) {
			Point point = entry.getKey();
			Point mirroredPoint = new Point(point.getX(), height - point.getY() - 1);
			PaperDot dot = entry.getValue();
			if (mirroredPoint.getY() < yOffset) {
				mirror.addDot(mirroredPoint, dot);
			}
		}
		return mirror;
	}

	public Set<Point> getPoints() {
		return dots.keySet();
	}

	public PaperDot getDot(Point p) {
		return dots.get(p);
	}

	public boolean contains(Point p) {
		return dots.containsKey(p);
	}

	public void fillEmpty() {
		for (int row=0; row<height; row++) {
			for (int col=0; col<width; col++) {
				dots.putIfAbsent(new Point(col, row), new PaperDot(true));
			}			
		}
	}
	
	@Override
	protected Paper clone() {
		Paper clone = new Paper();
		clone.dots = new HashMap<>(this.dots);
		clone.width = this.width;
		clone.height = this.height;
		return clone ;
	}

	public String render() {
		StringBuilder sb = new StringBuilder();
		for (int row=0; row<height; row++) {
			for (int col=0; col<width; col++) {
				sb.append(dots.get(new Point(col, row)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
