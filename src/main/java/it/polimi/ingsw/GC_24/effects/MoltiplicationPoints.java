package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationPoints extends Moltiplication{

	private Value value2;
	
	public MoltiplicationPoints(String name, SetOfValues effectValues, Value value, Value value2) {
		super(name, effectValues, value);
		this.value2 = value2;
	}

	@Override
	public void moltiplicationEffect(PersonalBoard playersBoard){
		int factor1 = value.getQuantity();
		int quanityplayervalue2 = value2.findValueInPlayer(playersBoard.getPlayer()).getQuantity();
		int dividervalue2 = value2.getQuantity();
		int factor2 = (quanityplayervalue2/dividervalue2);
		
		value.setQuantity(factor1*factor2);
		
		value.addValueToSet(playersBoard.getPlayer().getMyValues());
	}
}
