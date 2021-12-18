package aoc2021.days.day02;

public class AimUpCommand extends SubmarineCommand {

	@Override
	public void execute(Submarine submarine) {
		submarine.aimUp(amount);
	}

}
