package aoc2021.days.day11;

public class DumboOcotopusGridCell {
	private Long energyLevel;
	private boolean flashed;
	public DumboOcotopusGridCell(Long energyLevel) {
		this.energyLevel = energyLevel;
	}
	public void increaseEnergyLevel(long amount) {
		this.energyLevel += amount;
	}
	public long getEnergyLevel() {
		return this.energyLevel;
	}
	public boolean hasFlashed() {
		return flashed;
	}
	public void setFlashed(boolean flashed) {
		this.flashed = flashed;
	}
	public void reset() {
		this.energyLevel = 0L;
		this.flashed = false;
	}

}
