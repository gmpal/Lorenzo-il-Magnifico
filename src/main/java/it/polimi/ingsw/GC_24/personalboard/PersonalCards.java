package it.polimi.ingsw.GC_24.personalboard;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.PlayerColour;
import it.polimi.ingsw.GC_24.cards.*;

public abstract class PersonalCards {
	
	protected ArrayList<Card> cards;
	
	//constructor
	public PersonalCards(){
		this.cards= new ArrayList<Card>();
	}
	
	//finds an ArrayList of PersonalCards in the PersonalBoard if you don't already know the type
	public abstract PersonalCards FindCardsInPersonalBoard(PersonalBoard playersBoard);
	
	
	//getters and setters

	public void setCards(Card card){
		this.cards.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}

}
