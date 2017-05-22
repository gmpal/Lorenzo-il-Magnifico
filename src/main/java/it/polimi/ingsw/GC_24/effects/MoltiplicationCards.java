package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationCards extends Moltiplication{
	
	private PersonalCards personalCards;

	//constructor
	public MoltiplicationCards(String name, SetOfValues effectValues, Value value, PersonalCards personalCards) {
		super(name, effectValues, value);
		this.personalCards = personalCards;
	}
	
	@Override
	public void moltiplicationEffect(PersonalBoard playersBoard){
		int valueQuantity = this.value.getQuantity();
		PersonalCards correspondingArrayList = this.personalCards.findCardsInPersonalBoard(playersBoard);
		int sizeOfArray = correspondingArrayList.getCards().size();
		int newQuantity =(valueQuantity) * (sizeOfArray);
		value.setQuantity(newQuantity);
		SetOfValues setOfValues = playersBoard.getPlayer().getMyValues();
		value.addValueToSet(setOfValues);
	}
}
