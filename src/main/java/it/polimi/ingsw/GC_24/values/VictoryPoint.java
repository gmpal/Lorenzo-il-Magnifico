package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class VictoryPoint extends Value {

	//constructor
	public VictoryPoint(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setVictoryPoints(new VictoryPoint(values.getVictoryPoints().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getVictoryPoints();
	}
}
