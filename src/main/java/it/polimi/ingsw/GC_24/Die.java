package it.polimi.ingsw.GC_24;

public class Die {
	
	private int value; 
	private DieColour colour;
	
	//constructor
	public Die(int value, DieColour colour){
		this.value = value;
		this.colour = colour;
	}
	
	//getters and setters
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
