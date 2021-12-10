package aoc2021.day10;

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
