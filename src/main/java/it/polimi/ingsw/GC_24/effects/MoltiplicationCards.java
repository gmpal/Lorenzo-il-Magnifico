package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class MoltiplicationCards extends Moltiplication{
	
	private PersonalCards personalCards;

	public MoltiplicationCards(String name, SetOfValues effectValues, PersonalBoard playersBoard, Value value, PersonalCards personalCards) {
		super(name, effectValues, playersBoard, value);
		this.personalCards = personalCards;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void moltiplicationEffect(){
		value.setQuantity((this.value.getQuantity()) * (this.personalCards.FindCardsInPersonalBoard(playersBoard).getCards().size()));
		value.addValueToSet(playersBoard.getPlayer().getMyValues());
	}
}
