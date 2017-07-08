package it.polimi.ingsw.GC_24.model.personalboard;

import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public class PersonalTerritories extends PersonalCards {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9186662745127500426L;

	// constructor
	public PersonalTerritories() {
		super();
	}

	@Override
	public PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard) {
		return playersBoard.getPersonalTerritories();
	}

	@Override
	public String toString() {
		return "Territories " + getCards();
	}

	/**
	 * this conversion to victory points is based on the amount of territories one
	 * get during the game. There are some quantity steps until reaching the maximum
	 * points that's 20. For instance, with 3 cards the player gets 1 victory point,
	 * but with 6 (the maximum they can get) they receive 20.
	 */
	@Override
	public VictoryPoint convertCardToVictoryPoints() {
		int numTerritories = this.getCards().size();
		if (numTerritories == 3) {
			return new VictoryPoint(1);
		} else if (numTerritories == 4) {
			return new VictoryPoint(4);
		} else if (numTerritories == 5) {
			return new VictoryPoint(10);
		} else if (numTerritories == 6) {
			return new VictoryPoint(20);
		} else {
			return new VictoryPoint(0);
		}
	}

	@Override
	public String getType() {
		return "Territories";
	}
}
