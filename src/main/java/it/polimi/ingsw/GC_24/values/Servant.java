package it.polimi.ingsw.GC_24.values;

import it.polimi.ingsw.GC_24.model.Player;

public class Servant extends Value {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3877764112115951326L;

	//constructor
	public Servant(int value) {
		super(value);
	}
	
	@Override
	public SetOfValues addValueToSet(SetOfValues values){
		values.setServants(new Servant(values.getServants().getQuantity()+this.getQuantity()));
		return values;
	} 

	@Override
	public Value findValueInPlayer(Player player){
		return player.getMyValues().getServants();
	}
}
