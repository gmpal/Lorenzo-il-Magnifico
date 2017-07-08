package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.effects.Effect;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalCards;

/**
 * this effect is a malus that can be activated after the Vatican
 * excommunication. The player won't receive any victory point as an effect of a
 * card
 */
public class NoVictoryPointsFromCard extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6993408132225161776L;
	private PersonalCards typeOfCard;

	// constructor
	public NoVictoryPointsFromCard(String name, PersonalCards typeOfCard) {
		super(name);
		this.typeOfCard = typeOfCard;
	}

	// getters and setters
	public PersonalCards getTypeOfCard() {
		return typeOfCard;
	}

	public void setTypeOfCard(PersonalCards typeOfCard) {
		this.typeOfCard = typeOfCard;
	}
}
