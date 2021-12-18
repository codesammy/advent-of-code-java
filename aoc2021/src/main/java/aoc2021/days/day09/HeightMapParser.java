package aoc2021.days.day09;

import java.util.List;

public class HeightMapParser {

	public HeightMap parse(List<String> lines) {
		HeightMap map = new HeightMap();
		for (int x = 0; x < lines.size(); x++) {
			String line = lines.get(x);
			for (int y = 0; y < line.length(); y++) {
				int heightValue = line.charAt(y)-'0';
				HeightMapPoint p = new HeightMapPoint(x, y, heightValue);
				map.add(p);
			}
		}
		return map;
	}

}
