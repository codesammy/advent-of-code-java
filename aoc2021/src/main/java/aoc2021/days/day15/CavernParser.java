package aoc2021.days.day15;

import java.util.List;

import aoc2021.days.day05.Point;

public class CavernParser {

	public Cavern parse(List<String> lines) {
		Cavern map = new Cavern();
		for (int x = 0; x < lines.size(); x++) {
			String line = lines.get(x);
			for (int y = 0; y < line.length(); y++) {
				int riskLevel = line.charAt(y)-'0';
				Point p = new Point(x, y);
				map.add(p, riskLevel);
			}
		}
		return map;
	}

}
