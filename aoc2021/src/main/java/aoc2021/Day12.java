package aoc2021;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aoc2021.days.day12.Cave;
import aoc2021.days.day12.CaveParser;
import aoc2021.days.day12.CavePath;
import aoc2021.days.day12.CavePathFinder;
import aoc2021.days.day12.CavePathNextMoves;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day12 extends BaseDay {
	
	/**
	 * How many paths through this cave system are there that visit small caves at most once?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		CaveParser parser = new CaveParser();
		Set<Cave> caves = parser.parse(lines);
		CavePathFinder pathFinder = new CavePathFinder(caves);
		pathFinder.findAllPaths(new CavePathNextMoves() {
			
			@Override
			public Set<CavePath> findNextMoves(CavePath candidate) {
				Set<CavePath> nextMoves = new HashSet<>();
				Set<Cave> connectedCaves = candidate.getLast().getConnectedCaves();
				for (Cave cave : connectedCaves) {
					if (cave.isSmallCave() && candidate.contains(cave)) {
						continue;
					}
					CavePath nextMove = candidate.clone();
					nextMove.append(cave);
					nextMoves.add(nextMove);
				}
				return nextMoves;
			}
		});

		return ""+pathFinder.getPathCount();
	}

	/**
	 * Given these new rules, how many paths through this cave system are there?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		CaveParser parser = new CaveParser();
		Set<Cave> caves = parser.parse(lines);
		CavePathFinder pathFinder = new CavePathFinder(caves);
		pathFinder.findAllPaths(new CavePathNextMoves() {
			
			@Override
			public Set<CavePath> findNextMoves(CavePath candidate) {
				Set<CavePath> nextMoves = new HashSet<>();
				Set<Cave> connectedCaves = candidate.getLast().getConnectedCaves();
				for (Cave cave : connectedCaves) {
					if (cave.getName().equals("start")) {
						continue;
					}
					if (cave.isSmallCave()) {
						int allowedCount = 2;
						if (candidate.hasTwiceSmallCave()) {
							allowedCount = 1;
						}
						if (candidate.count(cave) >= allowedCount) {
							continue;
						}
					}
					CavePath nextMove = candidate.clone();
					nextMove.append(cave);
					nextMoves.add(nextMove);
				}
				return nextMoves;
			}
		});

		return ""+pathFinder.getPathCount();
	}

}
