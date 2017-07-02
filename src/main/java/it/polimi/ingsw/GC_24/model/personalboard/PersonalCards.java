package it.polimi.ingsw.GC_24.model.personalboard;

import java.util.*;

import it.polimi.ingsw.GC_24.model.cards.*;
import it.polimi.ingsw.GC_24.model.values.VictoryPoint;

public abstract class PersonalCards implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2709524868772549247L;
	private List<Development> cards;
	
	//constructor
	public PersonalCards(){
		this.cards = new ArrayList<>();
	}
	
	/**finds an ArrayList of PersonalCards in the PersonalBoard if you don't already know the type
	 * It's called on a specific PersonalCard and returns 
	 * the corresponding subClass of PersonalCards in a specific Personal board */
	public abstract PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard);
	
	public abstract  VictoryPoint convertCardToVictoryPoints();
	
	//getters and setters
	public void setCards(Development card){
		this.cards.add(card);
	}
	
	public List<Development> getCards(){
		return cards;
	}

	public abstract String getType();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for(Development d:cards){
			builder.append("[" + d.toString() + "]");
		}
		builder.append("\n");
		return builder.toString();
	}

}