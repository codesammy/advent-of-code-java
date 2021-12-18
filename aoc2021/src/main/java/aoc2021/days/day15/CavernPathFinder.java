package aoc2021.days.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import aoc2021.days.day05.Point;

public class CavernPathFinder {

	private Cavern cavern;

	public CavernPathFinder(Cavern cavern) {
		this.cavern = cavern;
	}

	public CavernPath findLowestRiskPath() {
		Node start = new Node(new Point(0, 0));
		Node end = new Node(cavern.getEndPoint());
		
		PriorityQueue<Node> openlist = new PriorityQueue<>();
		Set<Node> closedlist = new HashSet<>();
		Map<Node, Node> predecessor = new HashMap<>();
		Map<Point, Long> totalDistance = new HashMap<>();
		for (Point p : cavern.getPoints()) {
			if (p.getX() == 8 && p.getY() == 5) {
				System.out.println("8,5 set to max value");
			}
			totalDistance.put(p, Long.MAX_VALUE);
		}
		totalDistance.put(start.getPoint(), 0L);
		
		start.setF(0);
		openlist.offer(start);
		boolean first = true;
		while (!openlist.isEmpty() || first) {
			first = false;
	        Node current = openlist.remove();
	        // Wurde das Ziel gefunden?
	        if (current.equals(end)) {
	        	System.out.println("Path found!");
	            break;
	        }
	        closedlist.add(current);
	        
	        Set<Point> neighbors = cavern.getNeighbors(current.getPoint());
			for (Point neighborX : neighbors) {
				Node neighbor = new Node(neighborX);
		        // wenn der Nachfolgeknoten bereits auf der Closed List ist – tue nichts
		        if (closedlist.contains(neighbor)) {
		            continue;
		        }
		        // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
		        // die Kosten der gerade benutzten Kante
		        long tentative_g = totalDistance.get(current.getPoint()) + c(current, neighbor);
		        // wenn der Nachfolgeknoten bereits auf der Open List ist,
		        // aber der neue Weg nicht besser ist als der alte – tue nichts
		        if (openlist.contains(neighbor) && tentative_g >= totalDistance.get(neighborX)) {
		            continue;
		        }
		        // Vorgängerzeiger setzen und g Wert merken oder anpassen
		        predecessor.put(neighbor, current);
				
		        totalDistance.put(neighborX, tentative_g);
		        // f-Wert des Knotens in der Open List aktualisieren
		        // bzw. Knoten mit f-Wert in die Open List einfügen
		        neighbor.setF(tentative_g + h(neighbor));
		        if (openlist.contains(neighbor)) {
		        	openlist.remove(neighbor);
		        }
				openlist.offer(neighbor);
			}
		}
		
		List path = new ArrayList<>();
		Node reverse = end;
		while (!reverse.equals(start)) {
			path.add(reverse.getPoint());
			reverse = predecessor.get(reverse);
		}
		Collections.reverse(path);
		CavernPath minPath = new CavernPath(cavern, path);
		return minPath;
	}
	
	private int h(Node neighbor) {
		return cavern.distance(neighbor.getPoint(), cavern.getEndPoint());
	}

	private int c(Node current, Node neighbor) {
		return cavern.getTotalRiskLevel(neighbor.getPoint());
	}

	private int g(Node current, Map<Node, Node> predecessor) {
		int cost = 0;
		Node start = new Node(new Point(0, 0));
		Node reverse = current;
		while (!reverse.equals(start)) {
			cost += cavern.getTotalRiskLevel(reverse.getPoint());
			reverse = predecessor.get(reverse);
		}
		return cost;
	}

	public CavernPath findLowestRiskPathDjkstra() {
		Point start = new Point(0, 0);
		Point end = cavern.getEndPoint();
		
		Map<Point, Long> totalDistance = new HashMap<>();
		for (Point p : cavern.getPoints()) {
			totalDistance.put(p, Long.MAX_VALUE);
		}
		totalDistance.put(start, 0L);
		
		Map<Point, Point> predecessor = new HashMap<>();
		Set<Point> toBeVisited = new HashSet<>(cavern.getPoints());
		boolean started = false;
		int i = 0;
		while (!toBeVisited.isEmpty()) {
			if (i++%100==0)
				System.out.println("" + toBeVisited.size() + " left " + new Date().getTime());
			Point current = toBeVisited.stream().min((a,b) -> totalDistance.get(a).compareTo(totalDistance.get(b))).get();
			toBeVisited.remove(current);
			Set<Point> neighbors = cavern.getNeighbors(current);
			for (Point neighbor : neighbors) {
				Long possibleNewRisk = totalDistance.get(current) + cavern.getTotalRiskLevel(neighbor).longValue();
				if (possibleNewRisk < totalDistance.get(neighbor)) {
					predecessor.put(neighbor, current);
					totalDistance.put(neighbor, possibleNewRisk);
				}
			}
		}
		
		List path = new ArrayList<>();
		Point reverse = end;
		while (!reverse.equals(start)) {
			path.add(reverse);
			reverse = predecessor.get(reverse);
		}
		Collections.reverse(path);
		CavernPath minPath = new CavernPath(cavern, path);
		return minPath;
	}

}
