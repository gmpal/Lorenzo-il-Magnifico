package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public abstract class ActivityPlace extends Place{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6187260072256801458L;
	
	private int additionalCostDice;
	
	//constructor
	public ActivityPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);			
		this.additionalCostDice = additionalCostDice;
	}
	
	//gives the harvest or production value of the
	//bonus tile and the effects of the activated cards to the player 
	public abstract void doActivity(Player player);
	
	@Override 
	public void giveEffects(Player player){
		doActivity(player);
	}
	
	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}	
		
}
