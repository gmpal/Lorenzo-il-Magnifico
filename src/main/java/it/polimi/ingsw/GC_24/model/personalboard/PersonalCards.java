package it.polimi.ingsw.GC_24.model.personalboard;

import java.util.*;

import it.polimi.ingsw.GC_24.model.cards.*;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

/**
 * this abstract class represents all the type of cards that are given to the
 * player
 */
public abstract class PersonalCards implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2709524868772549247L;
	private List<Development> cards;

	// constructor
	public PersonalCards() {
		this.cards = new ArrayList<>();
	}

	/**
	 * finds an ArrayList of PersonalCards in the PersonalBoard if you don't already
	 * know the type It's called on a specific PersonalCard and returns the
	 * corresponding subClass of PersonalCards in a specific Personal board
	 */
	public abstract PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard);

	/**
	 * this method is overridden in the subclasses and it's used at the end of the
	 * game to calculate the amount of victory points one can receive from their
	 * cards
	 */
	public abstract VictoryPoint convertCardToVictoryPoints();

	/**
	 * this method is called from a Personal Card and returns the String type of
	 * that card
	 */
	public abstract String getType();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (Development d : cards) {
			builder.append("[" + d.toString() + "]");
		}
		builder.append("\n");
		return builder.toString();
	}

	// getters and setters
	public void setCards(Development card) {
		this.cards.add(card);
	}

	public List<Development> getCards() {
		return cards;
	}
}
