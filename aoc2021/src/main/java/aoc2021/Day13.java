package aoc2021;

import java.util.List;

import aoc2021.days.day13.FoldInstruction;
import aoc2021.days.day13.Paper;
import aoc2021.days.day13.PaperFolder;
import aoc2021.days.day13.PaperParser;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day13 extends BaseDay {
	
	/**
	 * How many dots are visible after completing just the first fold instruction on your transparent paper?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		PaperParser parser = new PaperParser();
		parser.parse(lines);
		Paper paper = parser.getPaper();
		List<FoldInstruction> instructions = parser.getFoldInstructions();
		PaperFolder folder = new PaperFolder(paper);
		folder.fold(instructions.get(0));
		return ""+folder.getPaper().getDotCount();
	}

	/**
	 * Finish folding the transparent paper according to the instructions.
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		PaperParser parser = new PaperParser();
		parser.parse(lines);
		Paper paper = parser.getPaper();
		List<FoldInstruction> instructions = parser.getFoldInstructions();
		PaperFolder folder = new PaperFolder(paper);
		for (FoldInstruction foldInstruction : instructions) {
			folder.fold(foldInstruction);
		}
		System.out.println("--- after fold ---");
		System.out.println(folder.getPaper().render());
		// manual ocr lmao
		return "gold";
	}

}
