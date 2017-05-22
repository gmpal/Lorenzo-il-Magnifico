package it.polimi.ingsw.GC_24.personalboard;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.*;

public abstract class PersonalCards {
	
	protected ArrayList<Card> cards;
	
	//constructor
	public PersonalCards(){
		this.cards= new ArrayList<>();
	}
	
	/*finds an ArrayList of PersonalCards in the PersonalBoard if you don't already know the type
	It's called on a specific PersonalCard and returns 
	the corresponding subClass of PersonalCards in a specific Personal board */
	public abstract PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard);
	
	
	//getters and setters
	public void setCards(Card card){
		this.cards.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}

}
