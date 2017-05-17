package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalBoard;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Development extends Card{
	
	private SetOfValues cost;
	private String type;
	//private SpecialEffect specialEffect;
	//private PermanentEffect permanentEffect;
	private int round;
	
	
	//getter and setters
	public SetOfValues getCost() {
		return cost;
	}
	public void setCost(SetOfValues cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	
	
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
	}

}
