package aoc2021.day02;

public class AimDownCommand extends SubmarineCommand {

	@Override
	public void execute(Submarine submarine) {
		submarine.aimDown(amount);
	}

}
