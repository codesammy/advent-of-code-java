package aoc2021.days.day19;

import java.util.ArrayList;
import java.util.List;

public class ScannerResultParser {

	List<ScannerResult> results;
	
	public ScannerResultParser() {
		results = new ArrayList<>();
	}
	
	public void parse(List<String> lines) {
		ScannerResult result = null;
		for (String line : lines) {
			if (line.isEmpty()) {
				continue;
			}
			if (line.startsWith("---")) {
				if (result != null) {
					results.add(result);
				}
				result = new ScannerResult();
			} else {
				String[] coords = line.split(",");
				Point3D beacon = new Point3D(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
				result.addBeacon(beacon);
			}
		}
		results.add(result);
	}

	public List<ScannerResult> getResults() {
		return results;
	}

}
