package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalCards;

public class NoVictoryPointsFromCard extends Effect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6993408132225161776L;
	private PersonalCards typeOfCard;

	//constructor
	public NoVictoryPointsFromCard(String name, PersonalCards typeOfCard) {
		super(name);
		this.typeOfCard = typeOfCard;
	}

	//getters and setters
	public PersonalCards getTypeOfCard() {
		return typeOfCard;
	}

	public void setTypeOfCard(PersonalCards typeOfCard) {
		this.typeOfCard = typeOfCard;
	}
}
