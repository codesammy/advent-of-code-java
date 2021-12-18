package aoc2021.days.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChunkParser {

	private List<Character> firstIllegalCharacters;
	private List<String> autoCompletedClosingChunkChars;

	public List<Character> getFirstIllegalCharacters() {
		return firstIllegalCharacters;
	}

	public List<String> getAutoCompletedClosingChunkChars() {
		return autoCompletedClosingChunkChars;
	}

	public void parse(List<String> lines) {
//		parseLine("([{");
//		parseLine("([{]");
		parseLine("([]{})[");
//		for (String line : lines) {
//			parseLine(line);
//		}
	}
	
	private void parseLine(String line) {
		char[] tokens = line.toCharArray();
		List<ParseResult> results = new ArrayList<ParseResult>();
		parseChunks(tokens, results);
		System.out.println("parse result for line '"+line+"' is " + results);
	}

	Map<Character, Character> matchingClosingChunkChars = Map.of('(', ')', '[', ']', '{', '}', '<', '>');
	/**
	 * 
	 * @param tokens
	 * @param results
	 * @return size of chunk
	 */
	private int parseChunks(char[] tokens, List<ParseResult> results) {
		
		// try to parse as many chunks as possible
		int i = 0;
		Character openingChunkChar = null;
		Character expectedClosingChunkChar = null;
		if (matchingClosingChunkChars.keySet().contains(tokens[i])) {
			openingChunkChar = tokens[i];
			expectedClosingChunkChar = matchingClosingChunkChars.get(openingChunkChar);
		}
		
		while (i < tokens.length) {

			// exit condition 1: closing char at beginning of chunk
			if (openingChunkChar == null) {
				results.add(new ParseResult(openingChunkChar, null));
				return -1;
			}
			
			// exit condition 2: missing closing chars // tokens.length - i == 1
			if (tokens.length == 1) {
				results.add(new ParseResult(null, openingChunkChar));
				return -1;
			}

			// exit condition 3: empty chunk
			if (matchingClosingChunkChars.values().contains(tokens[i+1])
					&& tokens[i+1] == matchingClosingChunkChars.get(tokens[i])) {
				i += 2;
				continue;
			}
			
			// exit condition 6: matching closing char
			if (tokens[i] == expectedClosingChunkChar) {
				return i+1;
			}
			
			// exit condition 4: wrong closing char
			if (matchingClosingChunkChars.values().contains(tokens[i+1])
					&& tokens[i+1] != expectedClosingChunkChar) {
				results.add(new ParseResult(null, openingChunkChar));
				results.add(new ParseResult(tokens[i+1], null));
				return -1;
			}
			
			// try to parse the next complete chunk
			int inc = parseChunks(Arrays.copyOfRange(tokens, i+1, tokens.length), results);
			if (inc == -1) {
				// exit condition 5: missing closing char
				results.add(new ParseResult(null, openingChunkChar));
				return -1;
			} else {
				i += inc;
			}
		}
		
		return i+1;
	}

	public void unusedRegexParsing(List<String> lines) {
		Map<Character, Character> matchingClosingChunkChars = Map.of('(', ')', '[', ']', '{', '}', '<', '>');
		
		Pattern validChunkPattern = Pattern.compile("\\(\\)|\\[\\]|\\{\\}|\\<\\>");
		Pattern chunkPattern = Pattern.compile("[\\(\\[\\{\\<\\)][\\)\\]\\}\\>]");
		
		firstIllegalCharacters = new LinkedList<>();
		autoCompletedClosingChunkChars = new LinkedList<>();
		for (String line : lines) {
			while (!line.isEmpty()) {
				Matcher m = validChunkPattern.matcher(line);
				if (m.find()) {
					line = m.replaceAll(r -> "");
				} else /* incomplete or corrupted */ {
					Matcher me = chunkPattern.matcher(line);
					if (me.find()) /* corrupted */ {
						char wrongClosingChar = me.group().charAt(1);
						firstIllegalCharacters.add(wrongClosingChar);
					} else /* incomplete */ {
						final String incompleteLine = line;
						String autocompletion = IntStream.range(0, line.length())
								.mapToObj(i -> incompleteLine.charAt(incompleteLine.length()-1-i))
								.map(c -> String.valueOf(matchingClosingChunkChars.get(c)))
								.collect(Collectors.joining());
						autoCompletedClosingChunkChars.add(autocompletion);
					}
					break;
				}
			}
		}
	}

}
