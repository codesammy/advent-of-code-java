package aoc2021.days.day17;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import aoc2021.days.day05.Point;

public class StyleThrowSimulator {

	private Integer overallMaxHittingHeight;
	private Point probePosition;
	private TargetArea target;
	private Set<Point> distinctHittinghVelocities;

	public StyleThrowSimulator(Point probePosition, TargetArea target) {
		this.probePosition = probePosition;
		this.target = target;
		overallMaxHittingHeight = 0;
		distinctHittinghVelocities = new HashSet<>();
	}

	public Integer getMaxHittingHeight() {
		return overallMaxHittingHeight;
	}

	public void findMaxHittingHeight() {
		PriorityQueue<Point> needToTry = new PriorityQueue<>((a,b) -> 0);
		for (int x=0; x<target.getHighestX()*2; x++) {
			for (int y=target.getLowestY()*2; y<500; y++) {
				needToTry.add(new Point(x,y));
			}
		}
//		needToTry.add(new Point(5,7));
//		needToTry.add(new Point(6,3));
		while (!needToTry.isEmpty()) {
			Point velocity = needToTry.remove();
			simulateThrow(velocity);
		}
	}

	/**
	 * 
    The probe's x position increases by its x velocity.
    The probe's y position increases by its y velocity.
    Due to drag, the probe's x velocity changes by 1 toward the value 0; that is, it decreases by 1 if it is greater than 0, increases by 1 if it is less than 0, or does not change if it is already 0.
    Due to gravity, the probe's y velocity decreases by 1.

	 */
	private void simulateThrow(Point velocity) {
//		System.out.println("trying velocity " + velocity);
		Point start = velocity;
		int maxHeight = 0;
		Point position = new Point(probePosition.getX(), probePosition.getY());
		// simulate until
		while (true) {
			position = new Point(position.getX() + velocity.getX(), position.getY() + velocity.getY());
			velocity = new Point(velocity.getX() + (velocity.getX() > 0 ? -1 : (velocity.getX() == 0 ? 0 : -1)), velocity.getY() - 1);
//			System.out.println("position is " + position);
			// right of target area
			if (position.getX() > target.getHighestX() ) {
//				System.out.println("flew over target");
				break;
			}
			// under target area
			if (position.getY() < target.getLowestY()) {
//				System.out.println("flew under target");
				break;
			}
			
			// record max height per throw
			if (position.getY() > maxHeight) {
				maxHeight = position.getY();
			}
			
			// hitting target
			if (target.contains(position)) {
//				System.out.println("hitting target");
//				System.out.println(start.getX() + ", " + start.getY());
//				System.out.println("position " + position);
//				System.out.println("target " + target);
				
				if (maxHeight > overallMaxHittingHeight) {
					overallMaxHittingHeight = maxHeight;
				}
				distinctHittinghVelocities.add(start);
				break;
			}
		}
	}

	public int getDistinctHittingVelocityCount() {
		return distinctHittinghVelocities.size();
	}

}
