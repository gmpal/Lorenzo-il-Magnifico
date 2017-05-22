package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class Servant extends Value {

	public Servant(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setServants(new Servant(values.getServants().getQuantity()+this.getQuantity()));
		return values;
	} 

	@Override
	public Value FindValueInPlayer(Player player){
		return player.getMyValues().getServants();
	}
}
