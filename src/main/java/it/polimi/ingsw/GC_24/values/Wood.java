package it.polimi.ingsw.GC_24.values;

import java.io.Serializable;

import it.polimi.ingsw.GC_24.model.Player;

public class Wood extends Value  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2271433451577531200L;

	//constructor
	public Wood(int value){
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getWoods().setQuantity(values.getWoods().getQuantity() + this.getQuantity());
		return values;
	}
	
	@Override
	public SetOfValues subValuefromSet(SetOfValues values){
		values.getWoods().setQuantity(values.getWoods().getQuantity() - this.getQuantity());
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getWoods();
	}

	@Override
	public String whatValueAmI() {
		return "Wood";
	}
	
	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getWoods().getQuantity() >= this.quantity);
	}
	
	@Override
	public String toString(){
			return "Wood: " + this.getQuantity();
		
	}
}