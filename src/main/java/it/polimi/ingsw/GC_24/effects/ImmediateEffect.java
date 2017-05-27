package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public abstract class ImmediateEffect extends Effect{
	
	// this class gives to the player the resource immediate effect and the immediate special effect
	private SetOfValues effectValues;
	
	//constructor needed for subclasses
	public ImmediateEffect(String name, SetOfValues effectValues) {
		super(name);
		this.effectValues = effectValues;
	}

	//this method gives the immediate effect of the card to the player when called
	public abstract void giveImmediateEffect(Player player);
	
	//adds the immediate effect related set of values to the player's set (given as parameter) 
	public void giveEffectValues(Player player){
		if (this.effectValues!=null){ 
			this.effectValues.addTwoSetsOfValues(player.getMyValues());			
		}
	}
	
	//getters and setters
	public SetOfValues getEffectValues() {
		return effectValues;		
	}

	public void setEffectValues(SetOfValues effectValues) {
		this.effectValues = effectValues;
	}
						
}
