package aoc2021;

import java.util.List;

import aoc2021.day02.AimDownCommand;
import aoc2021.day02.AimForwardCommand;
import aoc2021.day02.AimUpCommand;
import aoc2021.day02.MoveDownCommand;
import aoc2021.day02.MoveForwardCommand;
import aoc2021.day02.MoveUpCommand;
import aoc2021.day02.Submarine;
import aoc2021.day02.SubmarineCommand;
import aoc2021.day02.SubmarineCommandParser;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day02 extends BaseDay {
	
	/**
	 * What do you get if you multiply your final horizontal position by your final depth?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		SubmarineCommandParser parser = new SubmarineCommandParser();
		parser.registerCommand("forward", MoveForwardCommand.class);
		parser.registerCommand("down", MoveDownCommand.class);
		parser.registerCommand("up", MoveUpCommand.class);
		
		Submarine sub = new Submarine();
		for (String line : lines) {
			SubmarineCommand command = parser.parseLine(line);
			command.execute(sub);
		}
		
		return String.valueOf(sub.getHorizontalPosition()*sub.getDepth());
	}
	
	/**
	 * What do you get if you multiply your final horizontal position by your final depth?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		SubmarineCommandParser parser = new SubmarineCommandParser();
		parser.registerCommand("forward", AimForwardCommand.class);
		parser.registerCommand("down", AimDownCommand.class);
		parser.registerCommand("up", AimUpCommand.class);
		
		Submarine sub = new Submarine();
		for (String line : lines) {
			SubmarineCommand command = parser.parseLine(line);
			command.execute(sub);
		}
		
		return String.valueOf(sub.getHorizontalPosition()*sub.getDepth());
	}
}
