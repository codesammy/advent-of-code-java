package aoc2021.days.day02;

public class AimDownCommand extends SubmarineCommand {

	@Override
	public void execute(Submarine submarine) {
		submarine.aimDown(amount);
	}

}
