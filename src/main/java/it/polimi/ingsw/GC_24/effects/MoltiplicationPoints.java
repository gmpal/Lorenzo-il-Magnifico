package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class MoltiplicationPoints extends Moltiplication{

	private Value value2;
	
	public MoltiplicationPoints(String name, SetOfValues effectValues, Value value, Value value2) {
		super(name, effectValues, value);
		this.value2 = value2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moltiplicationEffect(){
		value.setQuantity((this.value.getQuantity()) * ((this.value2.FindValueInPlayer(playersBoard.getPlayer()).getQuantity())/value2.getQuantity()));
		value.addValueToSet(playersBoard.getPlayer().getMyValues());
	}
}
