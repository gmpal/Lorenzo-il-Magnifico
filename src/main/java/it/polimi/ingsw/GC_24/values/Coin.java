package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Coin extends Value {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5476502152547873802L;

	//constructor
	public Coin(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setCoins(new Coin(values.getCoins().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getCoins();
	}

}
