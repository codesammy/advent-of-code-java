package aoc2021.days.day02;

public class MoveForwardCommand extends SubmarineCommand {
	@Override
	public void execute(Submarine submarine) {
		submarine.moveForward(amount);
	}
}