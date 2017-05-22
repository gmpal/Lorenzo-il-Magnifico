package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Territories extends Development{

	//constructor
	public Territories(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff, cost);
	}
	
	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
		personalBoard.getPersonalTerritories().setCards(this);
	}
}
