package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalBoard;
import it.polimi.ingsw.GC_24.values.Value;

public class Development extends Card{
	
	private Value cost[];
	private String type;
	
	
	public Value[] getCost() {
		return cost;
	}
	public void setCost(Value[] cost) {
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
	private SpecialEffect specialEffect;
	private PermanentEffect permanentEffect;
	private int round;
	
	
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
		
		
	}

}
