package aoc2021.day02;

public class MoveDownCommand extends SubmarineCommand {
	@Override
	public void execute(Submarine submarine) {
		submarine.moveDown(amount);
	}
}