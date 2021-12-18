package aoc2021.days.day02;

public class MoveUpCommand extends SubmarineCommand {
	@Override
	public void execute(Submarine submarine) {
		submarine.moveUp(amount);
	}
}