package it.polimi.ingsw.GC_24.model.dice;

public class Die implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7122713452179944440L;
	private int value;
	private DieColour colour;

	// constructor
	public Die(int value, DieColour colour) {
		this.value = value;
		this.colour = colour;
	}

	// getters and setters
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public DieColour getColour() {
		return colour;
	}

	public void setColour(DieColour colour) {
		this.colour = colour;
	}
}