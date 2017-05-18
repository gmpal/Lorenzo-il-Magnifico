package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalTerritories;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Territories extends Development{

	public Territories(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff, cost);
		// TODO Auto-generated constructor stub
	}
	
	public void setCardOnPersonalBoard(PersonalTerritories personalTerritories){
		personalTerritories.setCards(this);
	}
}
