package it.polimi.ingsw.GC_24.model.effects.immediate;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.model.values.*;

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
		;
		int valueQuantity = this.getValue().getQuantity();
		PersonalCards correspondingArrayList = this.personalCards.findCardsInPersonalBoard(player.getMyBoard());
		int sizeOfArray = correspondingArrayList.getCards().size();
		int newQuantity = (valueQuantity) * (sizeOfArray);
		getValue().setQuantity(newQuantity);
		SetOfValues setOfValues = player.getMyValues();
		getValue().addValueToSet(setOfValues);
		;
	}

	@Override
	public void giveImmediateEffect(Player player) {
		;
		moltiplicationEffect(player);
		;
	}

	@Override
	public String toString() {
		return "Moltiplication Cards: for your every " + personalCards.getType() + " card you will receive "
				+ getValue().toString();
	}

	// getters and setters
	public PersonalCards getPersonalCards() {
		return personalCards;
	}

	public void setPersonalCards(PersonalCards personalCards) {
		this.personalCards = personalCards;
	}
}