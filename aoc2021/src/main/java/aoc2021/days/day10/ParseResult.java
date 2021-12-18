package aoc2021.days.day10;

public class ParseResult {

	private Character unexpectedClosingChar;
	private Character unmatchedOpeningChar;
	
	public ParseResult() {
	}

	public ParseResult(Character unexpectedClosingChar, Character unmatchedOpeningChar) {
		this.unexpectedClosingChar = unexpectedClosingChar;
		this.unmatchedOpeningChar = unmatchedOpeningChar;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ParseResult [");
		if (unexpectedClosingChar != null) {
			sb.append("unexpectedClosingChar: ");
			sb.append(unexpectedClosingChar);
		}
		if (unexpectedClosingChar != null && unmatchedOpeningChar != null) {
			sb.append(", ");
		}
		if (unmatchedOpeningChar != null) {
			sb.append("unmatchedOpeningChar: ");
			sb.append(unmatchedOpeningChar);
		}
		sb.append("]");
		return sb.toString();	}

}
