package it.polimi.ingsw.GC_24.model.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Coin extends Value {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6532928366092412694L;

	// constructor
	public Coin(int value) {
		super(value);
	}

	@Override
	public SetOfValues addValueToSet(SetOfValues values) {
		values.getCoins().setQuantity(values.getCoins().getQuantity() + this.getQuantity());
		return values;
	}

	@Override
	public SetOfValues subValuefromSet(SetOfValues values) {
		values.getCoins().setQuantity(values.getCoins().getQuantity() - this.getQuantity());
		return values;
	}

	@Override
	public Value findValueInPlayer(Player player) {
		return player.getMyValues().getCoins();
	}

	@Override
	public String whatValueAmI() {
		return "Coin";
	}

	@Override
	public Boolean amIPresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getCoins().getQuantity() >= this.quantity);
	}
	
	@Override
	public String toString() {
		return "coins = " + getQuantity();
	}

}
