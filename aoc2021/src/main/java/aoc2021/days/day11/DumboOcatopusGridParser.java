package aoc2021.days.day11;

import java.util.List;

import aoc2021.days.day05.Point;

public class DumboOcatopusGridParser {

	public DumboOcotopusGrid parse(List<String> lines) {
		DumboOcotopusGrid grid = new DumboOcotopusGrid();
		for (int x=0; x<lines.size(); x++) {
			String line = lines.get(x);
			for (int y=0; y<line.length(); y++) {
				Long energyLevel = (long) (line.charAt(y)-'0');
				Point point = new Point(x, y);
				DumboOcotopusGridCell cell = new DumboOcotopusGridCell(energyLevel);
				grid.addGridCell(point, cell);
			}			
		}
		return grid;
	}

}
