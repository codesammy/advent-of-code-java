package aoc2021.day08;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SignalPatternDecoder {
	private Map<Set<Character>, Set<Integer>> possibleDigits;
	/**
	 * key is the signal
	 * value is the segment
	 */
	private Map<Character, Set<Character>> possibleWireConnection;
	
	public SignalPatternDecoder() {
		possibleDigits = new HashMap<>();
		possibleWireConnection = new HashMap<>();
		for (char i=0; i<7; i++) {
			possibleWireConnection.put((char)('a'+i), new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g')));
		}
	}

	public void train(List<Set<Character>> uniqueSignalPatterns) {
		findDigits1478(uniqueSignalPatterns);
		countSignalOccurrence(uniqueSignalPatterns);
		deduceWireConnections();
		deduceDigits();
	}

	private void deduceDigits() {
		// sanity check
		for (Entry<Character, Set<Character>> entry : possibleWireConnection.entrySet()) {
			if (entry.getValue().size() != 1) {
				System.out.println("signal " + entry.getKey() + " has the following possible connections: " + entry.getValue());
				throw new IllegalStateException("not all wire connections deduced yet");
			}
		}
		
		Set<Character> signalPattern0 = translateSegmentsToSignals("abcefg");
		possibleDigits.put(signalPattern0 , new HashSet<>(Arrays.asList(0)));
		
		Set<Character> signalPattern2 = translateSegmentsToSignals("acdeg");
		possibleDigits.put(signalPattern2 , new HashSet<>(Arrays.asList(2)));
		
		Set<Character> signalPattern3 = translateSegmentsToSignals("acdfg");
		possibleDigits.put(signalPattern3 , new HashSet<>(Arrays.asList(3)));
		
		Set<Character> signalPattern5 = translateSegmentsToSignals("abdfg");
		possibleDigits.put(signalPattern5 , new HashSet<>(Arrays.asList(5)));
		
		Set<Character> signalPattern6 = translateSegmentsToSignals("abdefg");
		possibleDigits.put(signalPattern6 , new HashSet<>(Arrays.asList(6)));
		
		Set<Character> signalPattern9 = translateSegmentsToSignals("abcdfg");
		possibleDigits.put(signalPattern9 , new HashSet<>(Arrays.asList(9)));
	}

	private Set<Character> translateSegmentsToSignals(String segments) {
		return segments.chars().mapToObj(x->signalsForSegment((char)x)).flatMap(x->x.stream()).collect(Collectors.toSet());
	}

	private void deduceWireConnections() {
		Set<Character> signalsForA = signalsForDigit(7);
		signalsForA.removeAll(signalsForDigit(1));
		char signalA = signalsForA.toArray(Character[]::new)[0];
		possibleWireConnection.put(signalA, new HashSet<>(Arrays.asList('a')));
		
		Set<Character> signalsForC = signalsForDigit(1);
		signalsForC.removeAll(signalsForSegment('f'));
		char signalC = signalsForC.toArray(Character[]::new)[0];
		possibleWireConnection.put(signalC, new HashSet<>(Arrays.asList('c')));
		
		Set<Character> signalsForD = signalsForDigit(4);
		signalsForD.removeAll(signalsForDigit(1));
		signalsForD.removeAll(signalsForSegment('b'));
		char signalD = signalsForD.toArray(Character[]::new)[0];
		possibleWireConnection.put(signalD, new HashSet<>(Arrays.asList('d')));
		
		Set<Character> signalsForG = signalsForDigit(8);
		signalsForG.removeAll(signalsForSegment('a'));
		signalsForG.removeAll(signalsForSegment('b'));
		signalsForG.removeAll(signalsForSegment('c'));
		signalsForG.removeAll(signalsForSegment('d'));
		signalsForG.removeAll(signalsForSegment('e'));
		signalsForG.removeAll(signalsForSegment('f'));
		char signalG = signalsForG.toArray(Character[]::new)[0];
		possibleWireConnection.put(signalG, new HashSet<>(Arrays.asList('g')));
	}

	private Set<Character> signalsForSegment(char segment) {
		return possibleWireConnection.entrySet().stream()
				.filter(e -> e.getValue().size() == 1 && e.getValue().contains(segment))
				.map(x->x.getKey())
				.collect(Collectors.toSet());
	}

	private Set<Character> signalsForDigit(int digit) {
		return new HashSet<>(possibleDigits.entrySet().stream()
		.filter(e -> e.getValue().size() == 1 && e.getValue().contains(digit))
		.findFirst().get().getKey());
	}

	private void countSignalOccurrence(List<Set<Character>> uniqueSignalPatterns) {
		Map<Character, Long> signalCounts = uniqueSignalPatterns.stream()
		.flatMap(x->x.stream())
		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for (Entry<Character, Long> signalCount : signalCounts.entrySet()) {
			Character signal = signalCount.getKey();
			switch (signalCount.getValue().intValue()) {
			case 4:
				possibleWireConnection.put(signal, new HashSet<>(Arrays.asList('e')));
				break;
			case 6:
				possibleWireConnection.put(signal, new HashSet<>(Arrays.asList('b')));
				break;
			case 7:
				possibleWireConnection.get(signal).removeIf(x->!Arrays.asList('d', 'g').contains(x));
				break;
			case 8:
				possibleWireConnection.get(signal).removeIf(x->!Arrays.asList('a', 'c').contains(x));
				break;
			case 9:
				possibleWireConnection.put(signal, new HashSet<>(Arrays.asList('f')));
				break;
			default:
			}
		}
	}

	/**
	 * deduce obvious numbers
	 * @param uniqueSignalPatterns
	 */
	private void findDigits1478(List<Set<Character>> uniqueSignalPatterns) {
		for (Set<Character> uniqueSignalPattern : uniqueSignalPatterns) {
			switch (uniqueSignalPattern.size()) {
				case 2:
					possibleDigits.put(uniqueSignalPattern, new HashSet<>(Arrays.asList(1)));
					for (Character signal : uniqueSignalPattern) {
						possibleWireConnection.get(signal).removeIf(x->!Arrays.asList('c', 'f').contains(x));
					}
					break;
				case 3:
					possibleDigits.put(uniqueSignalPattern, new HashSet<>(Arrays.asList(7)));
					for (Character signal : uniqueSignalPattern) {
						possibleWireConnection.get(signal).removeIf(x->!Arrays.asList('a', 'c', 'f').contains(x));
					}
					break;
				case 4:
					possibleDigits.put(uniqueSignalPattern, new HashSet<>(Arrays.asList(4)));
					for (Character signal : uniqueSignalPattern) {
						possibleWireConnection.get(signal).removeIf(x->!Arrays.asList('b', 'c', 'd', 'f').contains(x));
					}
					break;
				case 7:
					possibleDigits.put(uniqueSignalPattern, new HashSet<>(Arrays.asList(8)));
					break;
				default:
					possibleDigits.put(uniqueSignalPattern, new HashSet<>(Arrays.asList(0,2,3,5,6,9)));
					break;
			}
		}
	}

	public List<Integer> decode(List<Set<Character>> encodedOutputValues) {
		return encodedOutputValues.stream().map(x->this.decode(x)).collect(Collectors.toList());
	}
	
	private Integer decode(Set<Character> encodedOutputValue) {
		Set<Integer> possibleDigitsForOutputValue = possibleDigits.get(encodedOutputValue);
		if (possibleDigitsForOutputValue.size() == 1) {
			return possibleDigitsForOutputValue.toArray(Integer[]::new)[0];
		}
		// TODO for full decoding
		return -1;
	}

}
