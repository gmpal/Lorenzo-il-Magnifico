package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class VictoryPoint extends Value{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2268345424205673910L;

	//constructor
	public VictoryPoint(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getVictoryPoints().setQuantity(values.getVictoryPoints().getQuantity() + this.getQuantity());
		return values;
	}
	
	@Override
	public SetOfValues subValuefromSet(SetOfValues values){
		values.getVictoryPoints().setQuantity(values.getVictoryPoints().getQuantity() - this.getQuantity());
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getVictoryPoints();
	}

	@Override
	public String whatValueAmI() {
		return "VictoryPoint";
	}
	
	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getVictoryPoints().getQuantity() >= this.quantity);
	}
}
