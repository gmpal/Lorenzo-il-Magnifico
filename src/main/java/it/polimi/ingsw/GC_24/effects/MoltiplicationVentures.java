package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationVentures extends Moltiplication{
	
	public MoltiplicationVentures(String name, SetOfValues effectValues, PersonalBoard playersBoard, Value value) {
		super(name, effectValues, playersBoard, value);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void moltiplicationEffect(){
		value.setQuantity((this.value.getQuantity()) * (this.playersBoard.getPersonalVentures().getCards().size()));
		value.addValueToSet(playersBoard.getPlayer().getMyValues());
	}

}
