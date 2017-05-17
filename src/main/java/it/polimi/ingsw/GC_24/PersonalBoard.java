package it.polimi.ingsw.GC_24;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.*;

public class PersonalBoard {
	
	protected PlayerColour player; 
	protected ArrayList<Card> cards;
	
	
	
	public PersonalBoard(PlayerColour player){
		this.player=player;
		this.cards= new ArrayList<Card>();
	}
	
	public void setCards(Development card){
		this.cards.add(card);
	}
	
	public ArrayList<Card> getCards(ArrayList<Card> cards){
		return cards;
	}

}
