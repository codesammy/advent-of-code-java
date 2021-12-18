package aoc2021.days.day12;

import java.util.Set;

public interface CavePathNextMoves {
	Set<CavePath> findNextMoves(CavePath candidate);
}
