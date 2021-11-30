package aoc2021.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Combinations<T> implements Iterable<List<T>> {

	private List<T> elements;
	private int numberOfElementsPerCombination;

	public Combinations(List<T> elements, int numberOfElementsPerCombination) {
		this.elements = elements;
		this.numberOfElementsPerCombination = numberOfElementsPerCombination;
	}

	@Override
	public Iterator<List<T>> iterator() {
		return new CombinationIterator<T>(elements, numberOfElementsPerCombination);
	}

	public class CombinationIterator<U> implements Iterator<List<U>> {

		private int numberOfElementsPerCombination;
		private Iterator<int[]> combinations;
		private List<U> elements;

		public CombinationIterator(List<U> elements, int numberOfElementsPerCombination) {
			this.elements = elements;
			int numberOfElements = elements.size();
			this.numberOfElementsPerCombination = numberOfElementsPerCombination;
			combinations = new org.apache.commons.math3.util.Combinations(numberOfElements, numberOfElementsPerCombination).iterator();
		}

		@Override
		public boolean hasNext() {
			return combinations.hasNext();
		}

		@Override
		public List<U> next() {
			int[] order = combinations.next();
			List<U> combination = new ArrayList<>();
			for (int i = 0; i < numberOfElementsPerCombination; i++) {
				combination.add(elements.get(order[i]));
			}
			return combination;
		}
	}

}
