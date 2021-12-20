package aoc2021.days.day20;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import aoc2021.days.day05.Point;

public class InputImage {
	
	Map<Point, Boolean> pixels = new HashMap<>();

	public void addPixel(Point point, boolean b) {
		pixels.put(point, b);
	}

	public Long getLitPixelCount() {
		return pixels.values().stream().filter(b -> b).count();
	}

	public Map<Point, Boolean> getPixels(Set<Point> neighbors) {
		Map<Point, Boolean> pixels = new HashMap<>();
		for (Point neighbor : neighbors) {
			boolean value = false;
			if (this.pixels.containsKey(neighbor)) {
				value = this.pixels.get(neighbor);
			} else {
				value = this.pixels.get(new Point(0,0));
			}
			pixels.put(neighbor, value);
		}
		
		return pixels;
	}

	public Set<Point> getPixels() {
		return pixels.keySet();
	}

	public String render() {
		StringBuilder sb = new StringBuilder();
		int minX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).min().getAsInt();
		int maxX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).max().getAsInt();
		int minY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).min().getAsInt();
		int maxY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).max().getAsInt();
		for (int y=minY; y<=maxY; y++) {
			for (int x=minX; x<=maxX; x++) {
				boolean value = false;
				Point point = new Point(x, y);
				if (this.pixels.containsKey(point)) {
					value = this.pixels.get(point);
				}
				sb.append(value ? '#' : '.');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	public InputImage getPadded(int i) {
		InputImage result = new InputImage();
		int minX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).min().getAsInt();
		int maxX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).max().getAsInt();
		int minY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).min().getAsInt();
		int maxY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).max().getAsInt();
		
		Map<Point, Boolean> corners = getPixels(Set.of(
				new Point(minX+1, minY),
				new Point(maxX-1, minY),
				new Point(maxX-1, maxY),
				new Point(minX+1, maxY)
				));
		boolean defaultValue = corners.values().stream().allMatch(c -> c == true);
		
//		System.out.println("before padding:");
//		System.out.println(this.render());
		
		for (int y=minY-i; y<=maxY+i; y++) {
			for (int x=minX-i; x<=maxX+i; x++) {
				boolean value = defaultValue;
				Point point = new Point(x, y);
				if (this.pixels.containsKey(point)) {
					value = this.pixels.get(point);
				}
				result.addPixel(point, value);
			}
		}
		
//		System.out.println("after padding:");
//		System.out.println(result.render());
		return result;
	}

	public InputImage reduce() {
		InputImage result = new InputImage();
		Set<Point> litPixels = this.pixels.entrySet().stream()
				.filter(p -> p.getValue() == true)
				.map(Entry::getKey)
				.collect(Collectors.toSet());
		int minX = litPixels.stream().mapToInt(x -> x.getX()).min().getAsInt();
		int maxX = litPixels.stream().mapToInt(x -> x.getX()).max().getAsInt();
		int minY = litPixels.stream().mapToInt(y -> y.getY()).min().getAsInt();
		int maxY = litPixels.stream().mapToInt(y -> y.getY()).max().getAsInt();
		for (int y=minY; y<=maxY; y++) {
			for (int x=minX; x<=maxX; x++) {
				boolean value = false;
				Point point = new Point(x, y);
				if (this.pixels.containsKey(point)) {
					value = this.pixels.get(point);
				}
				result.addPixel(point, value);
			}
		}
		return result;
	}

	public InputImage removePadding(int i) {
		InputImage result = new InputImage();
		int minX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).min().getAsInt();
		int maxX = this.pixels.keySet().stream().mapToInt(x -> x.getX()).max().getAsInt();
		int minY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).min().getAsInt();
		int maxY = this.pixels.keySet().stream().mapToInt(y -> y.getY()).max().getAsInt();

//		System.out.println("before padding removal:");
//		System.out.println(this.render());
		
		for (int y=minY+i; y<=maxY-i; y++) {
			for (int x=minX+i; x<=maxX-i; x++) {
				boolean value = false;
				Point point = new Point(x, y);
				if (this.pixels.containsKey(point)) {
					value = this.pixels.get(point);
				}
				result.addPixel(point, value);
			}
		}
		
//		System.out.println("after padding removal:");
//		System.out.println(result.render());
		return result;
	}
	
}
