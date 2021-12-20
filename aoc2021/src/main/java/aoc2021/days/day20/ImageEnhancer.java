package aoc2021.days.day20;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import aoc2021.days.day05.Point;

public class ImageEnhancer {

	private BitSet enhancementAlgorithm;

	public ImageEnhancer(BitSet enhancementAlgorithm) {
		this.enhancementAlgorithm = enhancementAlgorithm;
	}

	public InputImage enhance(InputImage image) {
		InputImage outputImage = new InputImage();
		
		InputImage padded = image.getPadded(10);
		Set<Point> inputPixels = padded.getPixels();
		
		for (Point pixel : inputPixels) {
			Set<Point> neighbors = getNeighbors(pixel);
			int lookupValue = joinNeighbors(padded.getPixels(neighbors), pixel);
			boolean litPixel = enhancementAlgorithm.get(lookupValue);
			outputImage.addPixel(pixel, litPixel);
		}
//		System.out.println("after enhance");
//		System.out.println(outputImage.render());
		outputImage = outputImage.removePadding(3);
		outputImage = outputImage.reduce();
//		System.out.println("after reduce");
//		System.out.println(outputImage.render());
		return outputImage;
	}

	private int joinNeighbors(Map<Point, Boolean> pixels, Point center) {
		BitSet num = new BitSet(9);
		num.set(0, 8, false);
		for (Point p : pixels.keySet()) {
			if (pixels.get(p)) {
				int bitIndex = 8-((p.getX()-center.getX()+1)+3*(p.getY()-center.getY()+1));
				num.set(bitIndex, true);
			}
		}
		long[] longArray = num.toLongArray();
		if (longArray.length > 0) {
			return (int) longArray[0];
		}
		return 0;
	}

	private Set<Point> getNeighbors(Point p) {
		Set<Point> neighbors = new HashSet<>(Set.of(
				new Point(p.getX()-1, p.getY()-1),
				new Point(p.getX()+0, p.getY()-1),
				new Point(p.getX()+1, p.getY()-1),
				new Point(p.getX()-1, p.getY()+0),
				new Point(p.getX()+0, p.getY()+0),
				new Point(p.getX()+1, p.getY()+0),
				new Point(p.getX()-1, p.getY()+1),
				new Point(p.getX()+0, p.getY()+1),
				new Point(p.getX()+1, p.getY()+1)
				));
		return neighbors;
	}

}
