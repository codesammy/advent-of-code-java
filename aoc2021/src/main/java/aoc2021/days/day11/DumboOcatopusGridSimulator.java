package aoc2021.days.day11;

public class DumboOcatopusGridSimulator {

	private DumboOcotopusGrid state;
	private Long flashCount;
	private Integer stepCount;

	public DumboOcatopusGridSimulator(DumboOcotopusGrid initialState) {
		this.state = initialState;
		this.flashCount = 0L;
		this.stepCount = 0;
	}

	public void advanceNSteps(int stepCount) {
		DumboOcotopusGrid currentState = this.state;
		DumboOcotopusGrid nextState;
		for (int i=0; i<stepCount; i++) {
			nextState = currentState.clone();
			
			nextState.increaseEnergyLevel(1);
			long flashCount = Long.MAX_VALUE;
			while ((flashCount = nextState.processFlash()) > 0) {
				this.flashCount += flashCount;
			}
			nextState.resetFlashed(0);
			currentState = nextState;
			/*
			if (i < 10 || ((i+1) % 10) == 0) {
				System.out.println("After step " + (i+1)+ ":");
				System.out.println(currentState.render());
			}
			*/
		}
		this.state = currentState;
	}

	public Long getFlashCount() {
		return flashCount;
	}

	public Integer getStepCount() {
		return stepCount;
	}

	public void advanceUntilSynchronized() {
		while(!this.state.isAllFlashSynchronized()) {
			advanceNSteps(1);
			stepCount++;
		}
	}

}
