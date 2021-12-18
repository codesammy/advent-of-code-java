package aoc2021.days.day11;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2021.days.day05.Point;

public class DumboOcotopusGrid {
	private final int width = 10;
	private final int height = 10;
	private Map<Point, DumboOcotopusGridCell> cells;
	
	public DumboOcotopusGrid() {
		cells = new HashMap<>();
	}

	public void addGridCell(Point point, DumboOcotopusGridCell cell) {
		cells.put(point, cell);
	}
	
	@Override
	public DumboOcotopusGrid clone() {
		DumboOcotopusGrid clone = new DumboOcotopusGrid();
		clone.cells = new HashMap<>(this.cells);
		return clone;
	}

	public void increaseEnergyLevel(long amount) {
		increaseEnergyLevel(cells.values(), amount);
	}

	private void increaseEnergyLevel(Collection<DumboOcotopusGridCell> cells, long amount) {
		for (DumboOcotopusGridCell cell : cells) {
			cell.increaseEnergyLevel(amount);
		}
	}

	public long processFlash() {
		long flashCount = 0;
		for (Entry<Point, DumboOcotopusGridCell> entry : cells.entrySet()) {
			Point point = entry.getKey();
			DumboOcotopusGridCell cell = entry.getValue();
			if (cell.getEnergyLevel() > 9 && !cell.hasFlashed()) {
				flashCount++;
				cell.setFlashed(true);
				Set<Point> neighbors = getNeighbors(point);
				Set<DumboOcotopusGridCell> neighborCells = cells.entrySet().stream()
				.filter(e -> neighbors.contains(e.getKey()))
				.map(Entry::getValue)
				.collect(Collectors.toSet());
				increaseEnergyLevel(neighborCells, 1L);
			}
		}
		return flashCount;
	}

	private Set<Point> getNeighbors(Point point) {
		Set<Point> neighbors = new HashSet<>(Set.of(
				new Point(-1, -1),
				new Point(-1,  0),
				new Point(-1,  1),
				new Point( 0, -1),
				//new Point(0, 0),
				new Point( 0,  1),
				new Point( 1, -1),
				new Point( 1,  0),
				new Point( 1,  1)
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

	public void resetFlashed(int i) {
		for (Entry<Point, DumboOcotopusGridCell> entry : cells.entrySet()) {
			DumboOcotopusGridCell cell = entry.getValue();
			if (cell.hasFlashed()) {
				cell.reset();
			}
		}
	}
	
	public String render() {
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				DumboOcotopusGridCell cell = cells.get(new Point(x, y));
				sb.append(cell.getEnergyLevel() > 9 ? 9 : cell.getEnergyLevel());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean isAllFlashSynchronized() {
		return cells.values().stream().allMatch(c -> c.getEnergyLevel() == 0);
	}
	
}
