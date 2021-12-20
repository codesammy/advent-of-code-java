package aoc2021;

import java.util.BitSet;
import java.util.List;

import aoc2021.days.day18.SnailFishNumber;
import aoc2021.days.day19.BeaconMapResolver;
import aoc2021.days.day19.Point3D;
import aoc2021.days.day19.ScannerResult;
import aoc2021.days.day19.ScannerResultParser;
import aoc2021.days.day20.ImageEnhancementParser;
import aoc2021.days.day20.ImageEnhancer;
import aoc2021.days.day20.InputImage;
import aoc2021.helper.Combinations;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day20 extends BaseDay {
	
	/**
	 * Start with the original input image and apply the image enhancement
	 * algorithm twice, being careful to account for the infinite size of
	 * the images.
	 * How many pixels are lit in the resulting image?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		return solution(lines, 2);
	}

	/**
	 * Start again with the original input image and apply the image enhancement
	 * algorithm 50 times.
	 * How many pixels are lit in the resulting image?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		return solution(lines, 50);
	}

	private String solution(List<String> lines, int times) {
		ImageEnhancementParser parser = new ImageEnhancementParser();
		parser.parse(lines);
		InputImage image = parser.getImage();
		ImageEnhancer enhancer = new ImageEnhancer(parser.getEnhancementAlgorithm());
		for (int i=0; i<times; i++) {
			image = enhancer.enhance(image);
		}
		return ""+image.getLitPixelCount();
	}

}
