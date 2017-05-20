package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class MoltiplicationPoints extends Moltiplication{

	private Value value2;
	
	public MoltiplicationPoints(String name, SetOfValues effectValues, PersonalBoard playersBoard, Value value, Value value2) {
		super(name, effectValues, playersBoard, value);
		this.value2 = value2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moltiplicationEffect(){
		int factor1 = value.getQuantity();
		int quanityplayervalue2 = value2.FindValueInPlayer(playersBoard.getPlayer()).getQuantity();
		int dividervalue2 = value2.getQuantity();
		int factor2 = (quanityplayervalue2/dividervalue2);
		//value.setQuantity((this.value.getQuantity()) * ((this.value2.FindValueInPlayer(playersBoard.getPlayer()).getQuantity())/(value2.getQuantity())));
		value.setQuantity(factor1*factor2);
		value.addValueToSet(playersBoard.getPlayer().getMyValues());
	}
}
