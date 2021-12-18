package aoc2021.days.day13;

import java.util.ArrayList;
import java.util.List;

import aoc2021.days.day05.Point;

public class PaperParser {
	
	private Paper paper;
	private List<FoldInstruction> foldInstructions;

	public void parse(List<String> lines) {
		paper = new Paper();
		int separationLineIndex = lines.indexOf("");
		for (String dotLine : lines.subList(0, separationLineIndex)) {
			String[] dotComponents = dotLine.split(",");
			Point point = new Point(Integer.parseInt(dotComponents[0]), Integer.parseInt(dotComponents[1]));
			paper.addDot(point, new PaperDot(false));
		}
		paper.fillEmpty();
		
		foldInstructions = new ArrayList<>();
		for (String foldLine : lines.subList(separationLineIndex+1, lines.size())) {
			String[] foldComponents = foldLine.split(" ")[2].split("=");
			int intComponent = Integer.parseInt(foldComponents[1]);
			int xAmount = foldComponents[0].equals("x") ? intComponent : 0;
			int yAmount = foldComponents[0].equals("y") ? intComponent : 0;
			FoldInstruction foldInstruction = new FoldInstruction(xAmount, yAmount);
			foldInstructions.add(foldInstruction);
		}
	}

	public Paper getPaper() {
		return paper;
	}

	public List<FoldInstruction> getFoldInstructions() {
		return foldInstructions;
	}

}
