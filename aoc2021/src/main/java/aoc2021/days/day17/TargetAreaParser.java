package aoc2021.days.day17;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc2021.days.day05.Point;

public class TargetAreaParser {

	private TargetArea targetArea;

	// target area: x=144..178, y=-100..-76
	public void parse(List<String> lines) {
		String input = lines.get(0);
		Pattern compile = Pattern.compile("target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)");
		Matcher m = compile.matcher(input);
		m.find();
		Integer topLeftX = Integer.valueOf(m.group(1));
		Integer bottomRightX = Integer.valueOf(m.group(2));
		Integer bottomRightY = Integer.valueOf(m.group(3));
		Integer topLeftY = Integer.valueOf(m.group(4));
		targetArea = new TargetArea(new Point(topLeftX, topLeftY), new Point(bottomRightX, bottomRightY));
	}

	public TargetArea getTargetArea() {
		return targetArea;
	}

}
