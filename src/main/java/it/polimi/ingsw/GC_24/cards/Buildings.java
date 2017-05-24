package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Buildings extends Development{
	
	private int costDie;
	
	//constructor
	public Buildings(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost, int costDie ) {
		super(name, permeff, immeff, speceff, cost);
		this.costDie = costDie;
	}

	@Override
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
		personalBoard.getPersonalBuildings().setCards(this);
	}
	
	@Override
	public String toString() {
		return "Buildings [costDie=" + costDie + "]";
	}

	//getter and setter
	public int getCostDie() {
		return costDie;
	}

	public void setCostDie(int costDie) {
		this.costDie = costDie;
	}

}
