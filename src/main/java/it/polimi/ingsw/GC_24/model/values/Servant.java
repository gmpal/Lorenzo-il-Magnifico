package it.polimi.ingsw.GC_24.model.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Servant extends Value {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014119871839792430L;

	//constructor
	public Servant(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.getServants().setQuantity(values.getServants().getQuantity() + this.getQuantity());
		return values;
	} 

	@Override
	public SetOfValues subValuefromSet(SetOfValues values){
		values.getServants().setQuantity(values.getServants().getQuantity() - this.getQuantity());
		return values;
	} 
	
	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getServants();
	}

	@Override
	public String whatValueAmI() {
		return "Servant";
	}
	
	@Override
	public Boolean amIPresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getServants().getQuantity() >= getQuantity());
	}
	
	@Override
	public String toString(){
			return "servants = " + getQuantity();
		
	}
}
