package aoc2021.days.day12;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CavePath {
	public CavePath() {
		
	}
	
	public CavePath(Cave cave) {
		caves = new LinkedList<Cave>(List.of(cave));
	}

	private List<Cave> caves;

	public Cave getFirst() {
		return caves.get(0);
	}

	public Cave getLast() {
		return caves.get(caves.size()-1);
	}
	
	@Override
	public CavePath clone() {
		CavePath cavePath = new CavePath();
		cavePath.caves = new LinkedList<Cave>(this.caves);
		return cavePath;
	}

	public void append(Cave cave) {
		this.caves.add(cave);
	}

	public boolean contains(Cave cave) {
		return caves.contains(cave);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caves == null) ? 0 : caves.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CavePath other = (CavePath) obj;
		if (caves == null) {
			if (other.caves != null)
				return false;
		} else if (!caves.equals(other.caves))
			return false;
		return true;
	}

	public boolean hasTwiceSmallCave() {
		return caves.stream()
				.filter(c -> c.isSmallCave())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.values().stream()
				.anyMatch(c -> c == 2);
	}

	public long count(Cave cave) {
		return caves.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.getOrDefault(cave, 0L);
	}
	
	@Override
	public String toString() {
		return caves.stream()
				.map(Object::toString)
				.collect(Collectors.joining(","));
	}
	
}
