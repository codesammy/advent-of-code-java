package aoc2021.days.day08;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EntryParser {
	private List<Set<Character>> uniqueSignalPatterns;
	private List<Set<Character>> encodedOutputValue;

	/**
	 * @return the uniqueSignalPatterns
	 */
	public List<Set<Character>> getUniqueSignalPatterns() {
		return uniqueSignalPatterns;
	}

	/**
	 * @return the encodedOutputValue
	 */
	public List<Set<Character>> getEncodedOutputValue() {
		return encodedOutputValue;
	}

	public void parseLine(String line) {
		String[] parts = line.split(" ");
		List<String> partList = Arrays.asList(parts);
		uniqueSignalPatterns = convertToCharacterSet(partList.subList(0, 10));
		encodedOutputValue = convertToCharacterSet(partList.subList(11, 15));
	}

	private List<Set<Character>> convertToCharacterSet(List<String> subList) {
		return subList.stream().map(x->x.chars().mapToObj(e->(char)e).collect(Collectors.toSet())).collect(Collectors.toList());
	}

}
