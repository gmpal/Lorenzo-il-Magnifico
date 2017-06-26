package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.values.*;

public class MoltiplicationCards extends Moltiplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4807445464341088218L;

	private PersonalCards personalCards;

	// constructor
	public MoltiplicationCards(String name, Value value, PersonalCards personalCards) {
		super(name, value);
		this.personalCards = personalCards;
	}

	@Override
	public void moltiplicationEffect(Player player) {
		int valueQuantity = this.value.getQuantity();
		PersonalCards correspondingArrayList = this.personalCards.findCardsInPersonalBoard(player.getMyBoard());
		int sizeOfArray = correspondingArrayList.getCards().size();
		int newQuantity = (valueQuantity) * (sizeOfArray);
		value.setQuantity(newQuantity);
		SetOfValues setOfValues = player.getMyValues();
		value.addValueToSet(setOfValues);
	}

	@Override
	public void giveImmediateEffect(Player player) {
		moltiplicationEffect(player);
	}

	public PersonalCards getPersonalCards() {
		return personalCards;
	}

	public void setPersonalCards(PersonalCards personalCards) {
		this.personalCards = personalCards;
	}


	
}
