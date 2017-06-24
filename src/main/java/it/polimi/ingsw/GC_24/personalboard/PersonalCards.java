package it.polimi.ingsw.GC_24.personalboard;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.*;

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
	
	/*finds an ArrayList of PersonalCards in the PersonalBoard if you don't already know the type
	It's called on a specific PersonalCard and returns 
	the corresponding subClass of PersonalCards in a specific Personal board */
	public abstract PersonalCards findCardsInPersonalBoard(PersonalBoard playersBoard);
	
	
	//getters and setters
	public void setCards(Development card){
		this.cards.add(card);
	}
	
	public List<Development> getCards(){
		return cards;
	}

}
