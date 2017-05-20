package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.Player;

public class Stone extends Value {

	public Stone(int value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setStones(new Stone(values.getStones().getQuantity()+this.getQuantity()));
		return values;
	}
	
	@Override
	public Value FindValueInPlayer(Player player){
		return player.getMyValues().getStones();
	}
}
