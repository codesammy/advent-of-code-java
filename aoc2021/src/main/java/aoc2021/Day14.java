package aoc2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aoc2021.days.day14.PolymerParser;
import aoc2021.days.day14.PolymerRule;
import aoc2021.days.day14.Polymerizer;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day14 extends BaseDay {
	
	/**
	 * Apply 10 steps of pair insertion to the polymer template and find the 
	 * most and least common elements in the result.
	 * What do you get if you take the quantity of the most common element and 
	 * subtract the quantity of the least common element?
	 */
	@Input
	@Silver
	public String silver(List<String> lines) {
		PolymerParser parser = new PolymerParser();
		parser.parse(lines);
		String polymerTemplate = parser.getPolymerTemplate();
		Set<PolymerRule> rules = parser.getPolymerRules();
		Polymerizer polymerizer = new Polymerizer(polymerTemplate, rules);
		for (int i = 0; i < 10; i++) {
			polymerizer.insertStep();
		}
		List<Long> quantities = new ArrayList<>(polymerizer.getPolymerGroupByCount().values());
		Collections.sort(quantities);
		return ""+(quantities.get(quantities.size()-1) - quantities.get(0));
	}

	/**
	 * Apply 40 steps of pair insertion to the polymer template and find the 
	 * most and least common elements in the result. What do you get if you 
	 * take the quantity of the most common element and subtract the quantity 
	 * of the least common element?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		PolymerParser parser = new PolymerParser();
		parser.parse(lines);
		String polymerTemplate = parser.getPolymerTemplate();
//		polymerTemplate = "NN";
		Set<PolymerRule> rules = parser.getPolymerRules();
		Polymerizer polymerizer = new Polymerizer(polymerTemplate, rules);
		for (int i = 0; i < 20; i++) {
			polymerizer.insertStep();
		}
		
		// reset count
		Map<String, Long> polymerGroupByCount = polymerizer.getPolymerGroupByCount();
		for (String polymer : polymerGroupByCount.keySet()) {
			polymerGroupByCount.compute(polymer, (k,v) -> 0L);
		}
		
//		System.out.println(polymerGroupByCount);
		Map<String, Long> pairGroupByCount = polymerizer.getPairGroupByCount();
//		System.out.println(pairGroupByCount);
		List<String> pairs = polymerizer.getPairList();
//		System.out.println(pairs);
		String lastPair = pairs.get(pairs.size()-1);
//		System.out.println(lastPair);
		for (String pair : pairGroupByCount.keySet()) {
//			System.out.println(pair);
			
			Polymerizer pairPolymerizer = new Polymerizer(pair, rules);
			for (int i = 0; i < 20; i++) {
				pairPolymerizer.insertStep();
			}
			Map<String, Long> pairPolymerGroupByCount = pairPolymerizer.getPolymerGroupByCount();
//			System.out.println(pairPolymerGroupByCount);
			// exclude last polymer count except for last pair
			String firstPolymer = pairPolymerizer.getFirstPolymer();
			String lastPolymer = pairPolymerizer.getLastPolymer();
			for (String pairPolymer : pairPolymerGroupByCount.keySet()) {
//				System.out.println(pairPolymer);
				long pairPolymerCount = (pairPolymerGroupByCount.get(pairPolymer)) * (pairGroupByCount.get(pair));
				if (pairPolymer.equals(lastPolymer)) {
					// last pair
					if (pair.equals(lastPair)) {
//						System.out.println("  last pair is " + pair);
						pairPolymerCount -= pairGroupByCount.get(pair) - 1;
					} else {
						pairPolymerCount -= pairGroupByCount.get(pair);
					}
				}
				
//				System.out.println(pairPolymerCount);
				polymerGroupByCount.put(pairPolymer, polymerGroupByCount.get(pairPolymer) + pairPolymerCount);
			}
		}
//		System.out.println();
		System.out.println(polymerGroupByCount);
		List<Long> quantities = new ArrayList<>(polymerGroupByCount.values());
//		System.out.println(quantities);
		Collections.sort(quantities);
		return ""+(quantities.get(quantities.size()-1) - quantities.get(0));
	}

}
