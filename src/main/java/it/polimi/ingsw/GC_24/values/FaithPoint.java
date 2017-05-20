package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class FaithPoint extends Value {

	public FaithPoint(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setFaithPoints(new FaithPoint(values.getFaithPoints().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value FindValueInPlayer(Player player){
		return player.getMyValues().getFaithPoints();
	}

}
