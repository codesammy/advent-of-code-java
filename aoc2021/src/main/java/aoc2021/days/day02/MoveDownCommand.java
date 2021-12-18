package aoc2021.days.day02;

public class MoveDownCommand extends SubmarineCommand {
	@Override
	public void execute(Submarine submarine) {
		submarine.moveDown(amount);
	}
}