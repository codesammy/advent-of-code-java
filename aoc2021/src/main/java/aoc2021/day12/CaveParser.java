package aoc2021.day12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CaveParser {

	public Set<Cave> parse(List<String> lines) {
		Map<String, Cave> caves = new HashMap<>();
		for (String line : lines) {
			String[] caveNames = line.split("-");
			String aName = caveNames[0];
			String bName = caveNames[1];
			caves.putIfAbsent(aName, new Cave(aName));
			caves.putIfAbsent(bName, new Cave(bName));
			Cave a = caves.get(aName);
			Cave b = caves.get(bName);
			a.addConnected(b);
			b.addConnected(a);
		}
		return new HashSet<>(caves.values());
	}

}
