package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Wood extends Value {
	
	//constructor
	public Wood(int value){
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setWoods(new Wood(values.getWoods().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getWoods();
	}
}