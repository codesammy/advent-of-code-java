package aoc2021;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import aoc2021.days.day08.EntryParser;
import aoc2021.days.day08.SignalPatternDecoder;
import aoc2021.days.day09.HeightMap;
import aoc2021.days.day09.HeightMapBasin;
import aoc2021.days.day09.HeightMapParser;
import aoc2021.days.day09.HeightMapPoint;
import aoc2021.days.day15.Cavern;
import aoc2021.days.day15.CavernParser;
import aoc2021.days.day15.CavernPath;
import aoc2021.days.day15.CavernPathFinder;
import aoc2021.meta.BaseDay;
import aoc2021.meta.Gold;
import aoc2021.meta.Input;
import aoc2021.meta.Silver;

public class Day16 extends BaseDay {
	
	/**
	 * Decode the structure of your hexadecimal-encoded BITS transmission; what do you get if you add up the version numbers in all packets?
	 */
	@Input
	//@Silver
	public String silver(List<String> lines) {
		String input = lines.get(0);
		String binaryInput = new BigInteger("F"+input, 16).toString(2);
		binaryInput = binaryInput.substring(4);
		
		AtomicInteger index = new AtomicInteger(0);
		
		AtomicLong versionSum = new AtomicLong(0);
		AtomicLong exprResult = new AtomicLong(0);
		parsePackage(binaryInput, index, versionSum, exprResult, 0);
		return ""+versionSum;
	}

	private int parsePackage(String binaryInput, AtomicInteger index, AtomicLong versionSum, AtomicLong exprResult, int level) {
//		printPosition(binaryInput, index);
		if (binaryInput.length()-1 < index.get()+3) {
			System.out.println("Not enough data!");
			return 0;
		}
		if (binaryInput.substring(index.get()).replaceAll("0", "").isEmpty()) {
			System.out.println("Rest is zero!");
			return 0;
		}
		int version = consumeBitInt(binaryInput, index, 3);
//		System.out.println("version="+version);
		versionSum.addAndGet(version);
		int type = consumeBitInt(binaryInput, index, 3);
//		System.out.println("type="+type);
		int length = 6;
		if (type == 4) /*literal value*/ {
			StringBuilder valueStr = new StringBuilder();
			int i =0;
			while (true) {
				i++;
				int additionalBlock = consumeBitInt(binaryInput, index, 1);
				String string = binaryInput.substring(index.get(), index.get()+4);
				index.getAndAdd(4);
				valueStr.append(string);
				length+=5;
//				System.out.println("value part="+value);
				if (additionalBlock == 0) {
					break;
				}
			}
//			System.out.println("blocks read: " + i);
			for (int j=0; j<level; j++) {
				System.out.print("    ");
			}
//			System.out.println("number: " + valueStr.toString());
			System.out.println("number: " + Long.parseLong(valueStr.toString(), 2));
			
			exprResult.set(Long.parseLong(valueStr.toString(), 2));
		} else /*operator*/ {
			int lengthType = consumeBitInt(binaryInput, index, 1);
			length+=1;
//			System.out.println("lengthType="+lengthType);
			List<Long> numbers = new ArrayList<>();
			if (lengthType == 0) {
				int totalLengthOfSubPackets = consumeBitInt(binaryInput, index, 15);
//				System.out.println("totalLengthOfSubPackets="+totalLengthOfSubPackets);
				length+=15;
				int actualLengthOfSubPackets = 0;
				while (actualLengthOfSubPackets < totalLengthOfSubPackets) {
					AtomicLong intermediateResult = new AtomicLong(0);
					actualLengthOfSubPackets+=parsePackage(binaryInput, index, versionSum, intermediateResult, level+1);
					numbers.add(intermediateResult.get());
				}
				length+=totalLengthOfSubPackets;
			} else {
				int numberOfSubPackets = consumeBitInt(binaryInput, index, 11);
				length+=11;
//				System.out.println("numberOfSubPackets="+numberOfSubPackets);
				for (int i=0; i< numberOfSubPackets; i++) {
					AtomicLong intermediateResult = new AtomicLong(0);
					length += parsePackage(binaryInput, index, versionSum, intermediateResult, level+1);
					numbers.add(intermediateResult.get());
				}
			}
			
			String opName = switch (type) {
				case 0 -> "sum";
				case 1 -> "product";
				case 2 -> "min";
				case 3 -> "max";
				case 5 -> "greater than";
				case 6 -> "less than";
				case 7 -> "equals";
				default -> "---- UNKNOWN ----";
			};
			long result = switch (type) {
				case 0 -> numbers.stream().mapToLong(x->x).sum();
				case 1 -> numbers.stream().mapToLong(x->x).reduce((a,b) -> a*b).getAsLong();
				case 2 -> numbers.stream().mapToLong(x->x).min().getAsLong();
				case 3 -> numbers.stream().mapToLong(x->x).max().getAsLong();
				case 5 -> numbers.get(0) > numbers.get(1) ? 1 : 0;
				case 6 -> numbers.get(0) < numbers.get(1) ? 1 : 0;
				case 7 -> numbers.get(0).equals(numbers.get(1)) ? 1 : 0;
				default -> 0;
			};
			for (int i=0; i<level; i++) {
				System.out.print("    ");
			}
			System.out.println(opName + " " + numbers + " => " + result);
			exprResult.set(result);
		}
		return length;
	}

	private void printPosition(String binaryInput, AtomicLong index) {
		System.out.println(binaryInput);
		for (int i=0; i<index.get(); i++) {
			System.out.print(" ");
		}
		System.out.println("^");
	}

	private int consumeBitInt(String binaryInput, AtomicInteger index, int length) {
		return Integer.parseInt(binaryInput.substring(index.get(), index.getAndAdd(length)+length), 2);
	}

	/**
	 * What do you get if you evaluate the expression represented by your hexadecimal-encoded BITS transmission?
	 */
	@Input
	@Gold
	public String gold(List<String> lines) {
		StringBuilder sb = new StringBuilder();
		for (String input : lines) {
			sb.append(gold(input));
			sb.append("\n");
		}
		return ""+sb.toString().substring(0, sb.length()-1);
	}
	
	private String gold(String input) {
		String binaryInput = new BigInteger("F"+input, 16).toString(2);
		binaryInput = binaryInput.substring(4);
		System.out.println(binaryInput);
		AtomicInteger index = new AtomicInteger(0);
		
		AtomicLong versionSum = new AtomicLong(0);
		AtomicLong exprResult = new AtomicLong(0);
		parsePackage(binaryInput, index, versionSum, exprResult, 0);
		return ""+exprResult;
	}

}
