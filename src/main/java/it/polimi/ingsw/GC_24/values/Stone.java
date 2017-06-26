package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Stone extends Value {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -255180121973478016L;

	//constructor
	public Stone(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getStones().setQuantity(values.getStones().getQuantity() + this.getQuantity());
		return values;
	}
	
	@Override
	public SetOfValues subValuefromSet(SetOfValues values){
		values.getStones().setQuantity(values.getStones().getQuantity() - this.getQuantity());
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
	
	@Override
	public String toString(){
			return "stones = " + getQuantity();
		
	}
}
