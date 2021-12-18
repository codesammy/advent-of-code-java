package aoc2021.days.day14;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PolymerParser {

	private String polymerTemplate;
	private Set<PolymerRule> polymerRules;

	public String getPolymerTemplate() {
		return polymerTemplate;
	}

	public Set<PolymerRule> getPolymerRules() {
		return polymerRules;
	}

	public void parse(List<String> lines) {
		polymerTemplate = lines.get(0);
		
		polymerRules = new HashSet<>();
		for (String rule : lines.subList(2, lines.size())) {
			String[] ruleComponents = rule.split(" -> ");
			PolymerRule polymerRule = new PolymerRule(ruleComponents[0], ruleComponents[1]);
			polymerRules.add(polymerRule);
		}
		
	}

}
