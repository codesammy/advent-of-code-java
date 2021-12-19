package aoc2021.days.day19;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScannerResult {

	List<Point3D> beacons;
	
	public ScannerResult() {
		beacons = new ArrayList<>();
	}
	
	public void addBeacon(Point3D beacon) {
		beacons.add(beacon);
	}

	@Override
	public String toString() {
		return "ScannerResult [beacons=" + beacons + "]";
	}

	public ScannerResult alignBeacons(int i) {
		ScannerResult result = new ScannerResult();
		Point3D pointOfReference = beacons.get(i);
		for (Point3D point3d : beacons) {
			result.addBeacon(pointOfReference.minus(point3d));
		}
		return result;
	}

	public int overlap(ScannerResult other) {
		HashSet<Point3D> mine = new HashSet<Point3D>(beacons);
		HashSet<Point3D> others = new HashSet<Point3D>(other.beacons);
		mine.retainAll(others);
		return mine.size();
	}

	public int getBeaconCount() {
		return beacons.size();
	}

	public ScannerResult turnLeft() {
		ScannerResult result = new ScannerResult();
		for (Point3D point3d : beacons) {
			result.addBeacon(point3d.turnLeft());
		}
		return result;
	}

	public ScannerResult rollLeft() {
		ScannerResult result = new ScannerResult();
		for (Point3D point3d : beacons) {
			result.addBeacon(point3d.rollLeft());
		}
		return result;
	}

	public List<ScannerResult> getAllOrientations() {
		List<ScannerResult> result = new ArrayList<>();
		
		List<ScannerResult> sides = new ArrayList(); 
		for (int side=0; side<4; side++) {
			ScannerResult scannerAligned = this;
			for (int i=0; i<side; i++) {
				scannerAligned = scannerAligned.turnLeft();
			}
			sides.add(scannerAligned);
		}
		//up
		sides.add(this.turnLeft().rollLeft().turnLeft().turnLeft().turnLeft());
		//down
		sides.add(this.turnLeft().rollLeft().turnLeft());
		
		for (ScannerResult scannerAligned : sides) {
			for (int roll=0; roll<4; roll++) {
				for (int i=0; i<roll; i++) {
					scannerAligned = scannerAligned.rollLeft();
				}
				result.add(scannerAligned);
			}
		}
		return result;
	}

	public Point3D getBeacon(int i) {
		return beacons.get(i);
	}

	public ScannerResult displace(Displacement displacement) {
		ScannerResult result = new ScannerResult();
		for (Point3D point3d : this.getAllOrientations().get(displacement.getOrientation()).beacons) {
			result.addBeacon(point3d.add(displacement.getPosition()));
		}
		return result;
	}

	public Set<Point3D> getBeacons() {
		return new HashSet<>(beacons);
	}

}
