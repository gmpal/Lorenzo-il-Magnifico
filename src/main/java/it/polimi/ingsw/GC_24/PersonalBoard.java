package it.polimi.ingsw.GC_24;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.*;

public class PersonalBoard {
	
	protected PlayerColour playerColour; 
	protected ArrayList<Card> cards;
	

	public PersonalBoard(PlayerColour colour){
		this.playerColour=colour;
		this.cards= new ArrayList<Card>();
	}
	
	public void setCards(Development card){
		this.cards.add(card);
	}
	
	public ArrayList<Card> getCards(ArrayList<Card> cards){
		return cards;
	}

}
