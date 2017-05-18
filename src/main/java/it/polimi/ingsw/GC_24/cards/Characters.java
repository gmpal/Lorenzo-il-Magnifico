package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalCharacters;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Characters extends Development{

	public Characters(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff, cost);
		// TODO Auto-generated constructor stub
	}
	
	public void setCardOnPersonalBoard(PersonalCharacters personalCharacters){
		personalCharacters.setCards(this);
	}


}
