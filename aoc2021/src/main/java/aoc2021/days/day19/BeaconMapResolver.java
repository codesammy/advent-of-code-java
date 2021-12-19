package aoc2021.days.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.math3.util.Combinations;

public class BeaconMapResolver {

	private List<ScannerResult> results;
	private Set<Point3D> beacons;
	private Map<ScannerResult, Displacement> resolvedScanners;

	public BeaconMapResolver(List<ScannerResult> results) {
		this.results = results;
		resolvedScanners = new HashMap<>();
	}

	public int getBeaconCount() {
		return beacons.size();
	}

	public void resolve() {
		ScannerResult scanner0 = results.get(0);
		resolvedScanners.put(scanner0, new Displacement(new Point3D(0,0,0), 0));
		
		while (!resolvedScanners.keySet().containsAll(results)) {
			Map<ScannerResult, Displacement> newResolvedScanners = new HashMap<>();
			for (ScannerResult alreadyResolvedScanner : resolvedScanners.keySet()) {
				Set<ScannerResult> remaining = new HashSet<>(results);
				remaining.removeAll(resolvedScanners.keySet());
				Displacement resolvedScannerPos = resolvedScanners.get(alreadyResolvedScanner);
				// orient scanner
				alreadyResolvedScanner = alreadyResolvedScanner.getAllOrientations().get(resolvedScannerPos.getOrientation());
				for (ScannerResult scanner : remaining) {
					
					Displacement scannerPos = findPosition(alreadyResolvedScanner, scanner);
					if (scannerPos != null) {
						Point3D pos = resolvedScannerPos.getPosition().add(scannerPos.getPosition());
						int orientation = scannerPos.getOrientation();
						Displacement newResolvedScannerPos = new Displacement(pos , orientation);
						// store scanner position
						newResolvedScanners.put(scanner, newResolvedScannerPos);
					}
				}
			}
			resolvedScanners.putAll(newResolvedScanners);
		}
		
		beacons = new HashSet<>();
		for (ScannerResult scanner : resolvedScanners.keySet()) {
			Displacement displacement = resolvedScanners.get(scanner);
			Set<Point3D> scannerBeacons = scanner.displace(displacement).getBeacons();
			beacons.addAll(scannerBeacons);
		}
	}

	private Displacement findPosition(ScannerResult scanner0, ScannerResult scanner1) {
		// loop over all scanner 0 beacons to align to
		for (int a0=0; a0<scanner0.getBeaconCount(); a0++) {
			ScannerResult scanner0Aligned = scanner0.alignBeacons(a0);
			int maxOverlap = 0;
			
			// loop over all scanner 1 beacons to align to
			List<ScannerResult> scanner1Orientations = scanner1.getAllOrientations();
			
			for (ScannerResult scanner1Orientation : scanner1Orientations) {
				for (int a=0; a<scanner1Orientation.getBeaconCount(); a++) {
					ScannerResult scanner1AlignedOrientation = scanner1Orientation.alignBeacons(a);
				
					int overlap = scanner0Aligned.overlap(scanner1AlignedOrientation);
					if (overlap >= 12) {
	//					System.out.println("Match!");
	//					System.out.println("correct orientation of scanner1          : " + scanner1Orientation);
	//					System.out.println("scanner0 beacon  at pos a)               : " + scanner0.getBeacon(0));
	//					System.out.println("aligned at beacon                        : " + scanner1Orientation.getBeacon(a));
	//					System.out.println("correct orientation of scanner1 (aligned): " + scanner1AlignedOrientation);
	//					System.out.println("position of scanner1 (relative to 0)     : " + scanner0.getBeacon(0).minus(scanner1Orientation.getBeacon(a)));
//						System.out.println("orientation: " + scanner1Orientations.indexOf(scanner1Orientation));
						Point3D pos = scanner0.getBeacon(a0).minus(scanner1Orientation.getBeacon(a));
						return new Displacement(pos, scanner1Orientations.indexOf(scanner1Orientation));
					}
					if (overlap > maxOverlap) {
						maxOverlap = overlap;
					}
				}
			}
		}
		
		return null;
	}

	public Integer getLargestScannerDistance() {
		Set<Point3D> scannerPositions = resolvedScanners.values().stream().map(x->x.getPosition()).collect(Collectors.toSet());
		aoc2021.helper.Combinations<Point3D> combos = new aoc2021.helper.Combinations<Point3D>(new ArrayList<>(scannerPositions), 2);
		int maxManhattenDistance = 0;
		for (List<Point3D> bothScannerPositions : combos) {
			int distance = bothScannerPositions.get(0).distance(bothScannerPositions.get(1));
			maxManhattenDistance = Math.max(maxManhattenDistance, distance);
		}
		return maxManhattenDistance;
	}

}
