package it.polimi.ingsw.GC_24;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.cards.*;

public abstract class PersonalCards {
	
	protected PlayerColour playerColour; 
	protected ArrayList<Card> cards;
	
	//constructor
	public PersonalCards(PlayerColour colour){
		this.playerColour=colour;
		this.cards= new ArrayList<Card>();
	}
	
	//getters and setters
	public PlayerColour getPlayerColour() {
		return playerColour;
	}

	public void setPlayerColour(PlayerColour playerColour) {
		this.playerColour = playerColour;
	}

	public void setCards(Card card){
		this.cards.add(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}

}
