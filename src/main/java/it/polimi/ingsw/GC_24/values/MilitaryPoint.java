package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class MilitaryPoint extends Value {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1200923023908392199L;

	//constructor
	public MilitaryPoint(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getMilitaryPoints().setQuantity(values.getMilitaryPoints().getQuantity() + this.getQuantity());
		return values;
	} 
	
	@Override
	public SetOfValues subValuefromSet(SetOfValues values){
		values.getMilitaryPoints().setQuantity(values.getMilitaryPoints().getQuantity() - this.getQuantity());
		return values;
	} 
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getMilitaryPoints();
	}

	@Override
	public String whatValueAmI() {
		return "MilitaryPoint";
	}
	
	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getMilitaryPoints().getQuantity() >= this.quantity);
	}
	
	@Override
	public String toString() {
		return "militaryPoints = " + getQuantity();
	}
}
