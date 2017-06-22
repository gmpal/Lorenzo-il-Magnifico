package it.polimi.ingsw.GC_24.effects;

public class IncreaseDieValueActivity extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090358683522256263L;
	private int increaseDieValue;

	// constructor
	public IncreaseDieValueActivity(String name, int increaseDieValue) {
		super(name);
		this.increaseDieValue = increaseDieValue;
	}

	// getters and setters
	public int getIncreaseDieValue() {
		return increaseDieValue;
	}

	public void setIncreaseDieValue(int increaseDieValue) {
		this.increaseDieValue = increaseDieValue;
	}
}
