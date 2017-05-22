package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class MilitaryPoint extends Value {

	public MilitaryPoint(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setMilitaryPoints(new MilitaryPoint(values.getMilitaryPoints().getQuantity()+this.getQuantity()));
		return values;
	} 
	
	@Override
	public Value FindValueInPlayer(Player player){
		return player.getMyValues().getMilitaryPoints();
	}
}
