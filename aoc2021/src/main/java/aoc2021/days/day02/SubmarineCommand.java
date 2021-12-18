package aoc2021.days.day02;

public abstract class SubmarineCommand {
	protected int amount;
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public abstract void execute(Submarine submarine);
}