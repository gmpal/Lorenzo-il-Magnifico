package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class ValueEffect extends ImmediateEffect {
	
	private SetOfValues setOfValue;

	//constructor
	public ValueEffect(String name){
		super(name);
		this.setOfValue=new SetOfValues();		
	}
	
	//adds the immediate effect related set of values to the player's set (given as parameter) 
	@Override
	public void giveImmediateEffect(Player player) {
		if (this.setOfValue!=null){ 
			this.setOfValue.addTwoSetsOfValues(player.getMyValues());			
		}
	}
	
	//getters and setters
	public SetOfValues getEffectValues() {
		return setOfValue;		
	}

	public void setEffectValues(SetOfValues effectValues) {
		this.setOfValue = effectValues;
	}

}