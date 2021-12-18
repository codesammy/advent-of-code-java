package aoc2021.days.day14;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Polymerizer {

	private String polymer;
	private final Set<PolymerRule> rules;

	public Polymerizer(String polymerTemplate, Set<PolymerRule> rules) {
		this.polymer = polymerTemplate;
		this.rules = rules;
	}

	public void insertStep() {
		StringBuilder newPolymer = new StringBuilder();
		for (int i=0; i<polymer.length()-1; i++) {
			String pair = polymer.substring(i, i+2);
			PolymerRule rule = rules.stream().filter(r -> r.getPair().equals(pair)).findFirst().get();
			newPolymer.append(pair.substring(0, 1));
			newPolymer.append(rule.getInsertedPolymer());
			if (i == polymer.length()-2) {
				newPolymer.append(pair.substring(1, 2));
			}
		}
		polymer = newPolymer.toString();
	}

	public Map<String, Long> getPolymerGroupByCount() {
		return polymer.chars()
				.mapToObj(x -> String.valueOf(Character.valueOf((char)x)))
				.collect(Collectors.groupingBy(x->x, Collectors.counting()));
	}

	public Map<String, Long> getPairGroupByCount() {
		return IntStream.range(0, polymer.length()-1)
				.mapToObj(i -> polymer.substring(i, i+2))
				.collect(Collectors.groupingBy(x->x, Collectors.counting()));
	}

	public String getLastPolymer() {
		return String.valueOf(polymer.charAt(polymer.length()-1));
	}

	public List<String> getPairList() {
		return IntStream.range(0, polymer.length()-1)
				.mapToObj(i -> polymer.substring(i, i+2))
				.collect(Collectors.toList());
	}

	public String getFirstPolymer() {
		return String.valueOf(polymer.charAt(0));
	}

}
