package aoc2021.days.day20;

import java.util.BitSet;
import java.util.List;

import aoc2021.days.day05.Point;

public class ImageEnhancementParser {

	private BitSet imageEnhancementAlgorithm;
	private InputImage image;

	public void parse(List<String> lines) {
		String imageEnhancementAlgorithmStr = lines.get(0);
		imageEnhancementAlgorithm = new BitSet(imageEnhancementAlgorithmStr.length());
		for (int i=0; i<imageEnhancementAlgorithmStr.length(); i++) {
			char c = imageEnhancementAlgorithmStr.charAt(i);
			if (c == '#') {
				imageEnhancementAlgorithm.set(i, true);
			}
		}
		
		image = new InputImage();
		for (String line : lines.subList(2, lines.size())) {
			for (int i=0; i<line.length(); i++) {
				char c = line.charAt(i);
				image.addPixel(new Point(i, lines.indexOf(line)-2), c == '#');
			}
		}
	}

	public InputImage getImage() {
		return image;
	}

	public BitSet getEnhancementAlgorithm() {
		return imageEnhancementAlgorithm;
	}

}
