package aoc2021.days.day18;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnailFishNumber {
	private static int logLevel = 0;

	private String num;

	public SnailFishNumber(String num) {
		this.num = num;
	}

	public SnailFishNumber add(SnailFishNumber other) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(num);
		sb.append(",");
		sb.append(other);
		sb.append("]");
		SnailFishNumber newNum = new SnailFishNumber(sb.toString());
		newNum.reduce();
		return newNum;
	}

	public void reduce() {
//		if (num.length() > 80) {
//			System.exit(1);
//		}
		if (explode()) {
			reduce();
			return;
		} else if (split()) {
			reduce();
			return;
		} else {
			return;
		}
	}
	
	private boolean explode() {
		int openBracketCount = 0;
		for(int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			if (c == '[') {
				openBracketCount++;
			}
			if (c == ']') {
				openBracketCount--;
			}
			if (openBracketCount == 5 && c == '[') {
				String[] pair = getPair(i);
				int pairLength = 1 + pair[0].length() + 1 + pair[1].length() + 1;
//				System.out.println("Explode pair [" + pair[0] + "," + pair[1] + "]");
//				printPosition(i);
				
				int offset = 0;
				try {
					Long left = Long.valueOf(pair[0]);
					int l = i;
					while ((c == '[' || c == ']' || c == ',') && l >= 0) {
						c = num.charAt(l);
						l--;
					}
					l++;
					int le = l;
					while (le >= 0) {
						c = num.charAt(le);
						if (c == '[' || c == ']' || c == ',') {
							le++;
							break;
						}
						le--;
					}
//					printPosition(le);
//					printPosition(l);
					Long target = Long.valueOf(num.substring(le, l+1));
					Long update = left + target;
					
					StringBuilder sb = new StringBuilder(num);
					offset = update.toString().length() - ((l+1) - le);
					sb.replace(le, l+1, update.toString());
					num = sb.toString();
//					printPosition(l);
				} catch (NumberFormatException nfe) {
					// ignore
//					System.out.println(nfe);
				}
				
				try {
					Long right = Long.parseLong(pair[1]);
					int r = i + pairLength + 1;
//					System.out.println("r:" + offset);
//					printPosition(r);
					while (r < num.length()) {
						c = num.charAt(r);
						if (c != '[' && c != ']' && c != ',') {
							break;
						}
						r++;
					}
//					System.out.println("r:" + offset);
//					printPosition(r);
					int re = r;
					while (re < num.length()) {
						c = num.charAt(re);
						if (c == '[' || c == ']' || c == ',') {
							re--;
							break;
						}
						re++;
					}
					if (re < num.length()) {
						Long target = Long.valueOf(num.substring(r, re+1));
						Long update = right + target;
						//offset += update.toString().length() - target.toString().length();
	//					printPosition(r);
	//					printPosition(re);
						StringBuilder sb = new StringBuilder(num);
						sb.replace(r, re+1, update.toString());
						num = sb.toString();
					}
//					System.out.println(num);
//					printPosition(r);
				} catch (NumberFormatException nfe) {
					// ignore
				}
				
				// remove original number
				StringBuilder sb = new StringBuilder(num);
				sb.replace(i+offset, i+offset + pairLength, "0");
				num = sb.toString();
				
				if (logLevel > 0) {
					System.out.println("after explode:  " + num);
				}
//				System.out.println(num);
//				System.out.println();
//				System.exit(1);

				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param i at '['
	 * @return
	 */
	private String[] getPair(int i) {
		int e = i+1;
		char c;
		int openBracketCount = 1;
		while ((c = num.charAt(e)) != ']' || openBracketCount != 1) {
			if (c == '[') {
				openBracketCount++;
			}
			if (c == ']') {
				openBracketCount--;
			}
			e++;
		}
		// FIXME split may not be correct
		String[] pair = num.substring(i+1, e).split(",");
		return pair;
	}

	private void printPosition(int i) {
		System.out.println(num);
		for(int x=0; x<i; x++) {
			System.out.print(" ");
		}
		System.out.println("^");
	}

	private boolean split() {
		Pattern numPattern = Pattern.compile("[0-9]+");
		Matcher m = numPattern.matcher(num);
		while (m.find()) {
			Long n = Long.valueOf(m.group());
			String pair = "[" + (int)Math.floor(n / 2.0) + "," + (int)Math.ceil(n / 2.0) + "]";
			if (n > 9) {
//				printPosition(m.start());
//				printPosition(m.end());
				// remove original number m.group()
				StringBuilder sb = new StringBuilder(num);
				sb.replace(m.start(), m.end(), pair);
				num = sb.toString();
				
				if (logLevel > 0) {
					System.out.println("after split:    " + num);
				}
				return true;
			}
		}
		return false;
	}

	public Long calcMagnitude() {
		Pattern pairPattern = Pattern.compile("\\[[0-9]+,[0-9]+\\]");
		while (true) {
			Matcher m = pairPattern.matcher(num);
			if (m.find()) {
//				System.out.println(m.group());
				String[] pair = m.group().substring(1, m.group().length()-1).split(",");
				Long update = Long.valueOf(pair[0]) * 3 + Long.valueOf(pair[1]) * 2;
				StringBuilder sb = new StringBuilder(num);
//				System.out.println(update);
				sb.replace(m.start(), m.end(), update.toString());
				num = sb.toString();
//				System.out.println(num);
			} else {
				return Long.valueOf(num);
			}
		}
	}

	@Override
	public String toString() {
		return num;
	}

}
