package aoc2021.days.day02;

public class AimForwardCommand extends SubmarineCommand {

	@Override
	public void execute(Submarine submarine) {
		submarine.aimForward(amount);
	}

}
