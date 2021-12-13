package aoc2021.day12;

import java.util.HashSet;
import java.util.Set;

public class CavePathFinder {

	private Set<Cave> caves;
	private Set<CavePath> paths;

	public CavePathFinder(Set<Cave> caves) {
		this.caves = caves;
		paths = new HashSet<>();
	}

	public void findAllPaths(CavePathNextMoves cavePathNextMoves) {
		CavePath start = new CavePath(caves.stream()
				.filter(c -> c.getName().equals("start"))
				.findFirst()
				.get());
		Set<CavePath> candidates = new HashSet<>(Set.of(start));
		Set<CavePath> visited = new HashSet<>();
		while (!candidates.isEmpty()) {
			CavePath candidate = candidates.stream().findFirst().get();
			if (isCompletePath(candidate)) {
//				System.out.println(candidate);
				paths.add(candidate);
			} else {
				Set<CavePath> nextMoves = cavePathNextMoves.findNextMoves(candidate);
				nextMoves.removeAll(visited);
				nextMoves.removeAll(paths);
				if (!nextMoves.isEmpty()) {
					candidates.addAll(nextMoves);
				}
			}
			candidates.remove(candidate);
		}
	}

	private boolean isCompletePath(CavePath candidate) {
		return candidate.getFirst().getName().equals("start") && candidate.getLast().getName().equals("end");
	}

	public int getPathCount() {
		return paths.size();
	}

}
