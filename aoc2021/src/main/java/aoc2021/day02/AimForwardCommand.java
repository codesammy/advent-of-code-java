package aoc2021.day02;

public class AimForwardCommand extends SubmarineCommand {

	@Override
	public void execute(Submarine submarine) {
		submarine.aimForward(amount);
	}

}
