package aoc2021.days.day09;

import java.util.HashSet;
import java.util.Set;

public class HeightMapBasin {
	private Set<HeightMapPoint> locations;
	
	public HeightMapBasin(Set<HeightMapPoint> locations) {
		this.locations = locations;
	}
	
	public Integer getSize() {
		return locations.size();
	}
}
