package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.values.VictoryPoint;

public class PersonalCharacters extends PersonalCards {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8730181705703973672L;

	// constructor
	public PersonalCharacters() {
		super();
	}

	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard) {
		return playersBoard.getPersonalCharacters();
	}

	@Override
	public String toString() {
		return "Characters " + getCards();
	}

	@Override
	public VictoryPoint convertCardToVictoryPoints() {
		int numCharacters = this.getCards().size();
		return new VictoryPoint(numCharacters * ((numCharacters + 1) / 2));
	}
}
