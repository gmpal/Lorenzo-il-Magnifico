package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.values.Value;

public abstract class ActivityPlace extends Place{

	private int additionalCostDice;
	
	//constructor
	public ActivityPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);			
		this.additionalCostDice = additionalCostDice;
	}
	
	//gives the harvest or production value of the
	//bonus tile and the effects of the activated cards to the player 
	public abstract void doActivity();
	
	@Override 
	public void giveEffects(){
		doActivity();
	}
	
	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}	
		
}