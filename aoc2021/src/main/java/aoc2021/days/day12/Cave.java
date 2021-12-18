package aoc2021.days.day12;

import java.util.HashSet;
import java.util.Set;

public class Cave {
	private String name;
	private Set<Cave> connectedCaves;
	
	public Cave(String name) {
		this.name = name;
		connectedCaves = new HashSet<>();
	}

	public void addConnected(Cave cave) {
		connectedCaves.add(cave);
	}

	public Object getName() {
		return name;
	}

	public Set<Cave> getConnectedCaves() {
		return connectedCaves;
	}

	public boolean isSmallCave() {
		return name.equals(name.toLowerCase());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cave other = (Cave) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
