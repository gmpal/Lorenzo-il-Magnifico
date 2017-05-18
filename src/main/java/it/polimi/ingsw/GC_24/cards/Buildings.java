package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalBuildings;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Buildings extends Development{
	
	public Buildings(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff, cost);
		// TODO Auto-generated constructor stub
	}

	public void setCardOnPersonalBoard(PersonalBuildings personalBuildings){
		personalBuildings.setCards(this);
	}

}
