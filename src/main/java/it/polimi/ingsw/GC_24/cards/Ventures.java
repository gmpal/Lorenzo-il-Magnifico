package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalVentures;
import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Ventures extends Development{
	
	private MilitaryPoint requiredMilitaryPoints;
	
	public Ventures(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost, MilitaryPoint requiredMilitaryPoints) {
		super(name, permeff, immeff, speceff, cost);
		this.requiredMilitaryPoints = requiredMilitaryPoints;
		// TODO Auto-generated constructor stub
	}
	
	public void setCardOnPersonalBoard(PersonalVentures personalVentures){
		personalVentures.setCards(this);
	}

}
