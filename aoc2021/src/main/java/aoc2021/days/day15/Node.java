package aoc2021.days.day15;

import java.util.Objects;

import aoc2021.days.day05.Point;

public class Node implements Comparable<Node> {
	private Long f = Long.MAX_VALUE;
	private Point p;
	
	public Node(Point p) {
		this.p = p;
	}

	public Point getPoint() {
		return p;
	}

	@Override
	public int compareTo(Node o) {
		return f.compareTo(o.getF());
	}

	private Long getF() {
		return f;
	}

	public void setF(long l) {
		f = l;
	}

	@Override
	public int hashCode() {
		return Objects.hash(p);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(p, other.p);
	}
}
