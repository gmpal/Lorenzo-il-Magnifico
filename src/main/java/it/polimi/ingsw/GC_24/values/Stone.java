package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Stone extends Value {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1627504025084117272L;

	//constructor
	public Stone(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setStones(new Stone(values.getStones().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getStones();
	}

	@Override
	public String whatValueAmI() {
		return "Stone";
	}
	
	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getStones().getQuantity() >= this.quantity);
	}
}
