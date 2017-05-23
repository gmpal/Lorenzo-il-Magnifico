package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class Servant extends Value {

	//constructor
	public Servant(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setServants(new Servant(values.getServants().getQuantity()+this.getQuantity()));
		return values;
	} 

	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getServants();
	}
}
