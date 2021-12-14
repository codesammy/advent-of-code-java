package aoc2021.day13;

import aoc2021.day05.Point;

public class PaperFolder {

	private Paper paper;

	public PaperFolder(Paper paper) {
		this.paper = paper;
	}

	public void fold(FoldInstruction foldInstruction) {
		Paper newPaper = new Paper();
		Paper foldingLayer;
		if (foldInstruction.getXAmount() > 0) {
			foldingLayer = this.paper.clone().mirrorX(foldInstruction.getXAmount());
		} else {
			foldingLayer = this.paper.clone().mirrorY(foldInstruction.getYAmount());
		}
		for (Point p : paper.getPoints()) {
			if (foldingLayer.contains(p)) {
				PaperDot originalDot = paper.getDot(p);
				PaperDot foldedDot = foldingLayer.getDot(p);
				PaperDot newPaperDot = new PaperDot(originalDot.isEmpty() && foldedDot.isEmpty());
				newPaper.addDot(p, newPaperDot);
			}
		}
		paper = newPaper;
	}

	public Paper getPaper() {
		return paper;
	}

}
