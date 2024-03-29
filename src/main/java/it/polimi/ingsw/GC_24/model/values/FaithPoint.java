package it.polimi.ingsw.GC_24.model.values;

import java.util.List;
import it.polimi.ingsw.GC_24.model.Player;

public class FaithPoint extends Value {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3781295737830022673L;

	// constructor
	public FaithPoint(int value) {
		super(value);
	}

	@Override
	public SetOfValues addValueToSet(SetOfValues values) {
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() + this.getQuantity());
		return values;
	}

	@Override
	public SetOfValues subValuefromSet(SetOfValues values) {
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() - this.getQuantity());
		return values;
	}

	@Override
	public Value findValueInPlayer(Player player) {
		return player.getMyValues().getFaithPoints();
	}

	@Override
	public String whatValueAmI() {
		return "FaithPoint";
	}

	@Override
	public Boolean amIPresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getFaithPoints().getQuantity() >= this.quantity);
	}

	/**
	 * This method return the corresponding Values of Faith Points
	 */
	public SetOfValues convertToValue(List<SetOfValues> correspondingValue) {
		return correspondingValue.get(this.quantity);
	}
	
	@Override
	public String toString() {
		return "faithPoints = " + getQuantity();
	}
}
