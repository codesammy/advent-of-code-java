package aoc2021.days.day14;

public class PolymerRule {
	private String pair;
	private String insertedPolymer;
	
	public PolymerRule(String pair, String insertedPolymer) {
		this.pair = pair;
		this.insertedPolymer = insertedPolymer;
	}

	public String getInsertedPolymer() {
		return insertedPolymer;
	}

	public String getPair() {
		return pair;
	}
}
