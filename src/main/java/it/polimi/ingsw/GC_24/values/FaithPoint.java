package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class FaithPoint extends Value {

	//constructor
	public FaithPoint(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setFaithPoints(new FaithPoint(values.getFaithPoints().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getFaithPoints();
	}

}
