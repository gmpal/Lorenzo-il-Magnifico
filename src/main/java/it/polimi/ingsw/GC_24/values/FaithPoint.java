package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class FaithPoint extends Value {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3781295737830022673L;

	//constructor
	public FaithPoint(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() + this.getQuantity());
		return values;
	}
	
	@Override
	public SetOfValues subValuefromSet(SetOfValues values) {
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() - this.getQuantity());
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getFaithPoints();
	}

	@Override
	public String whatValueAmI() {
		return "FaithPoint";
	}
	
	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getFaithPoints().getQuantity() >= this.quantity);
	}
}
