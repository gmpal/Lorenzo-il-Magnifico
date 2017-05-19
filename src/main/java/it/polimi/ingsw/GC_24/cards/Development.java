package it.polimi.ingsw.GC_24.cards;

import it.polimi.ingsw.GC_24.PersonalBoard;
import it.polimi.ingsw.GC_24.PersonalCards;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class Development extends Card{
	
	private SetOfValues cost;
	private String type;
	//private SpecialEffect specialEffect;
	//private PermanentEffect permanentEffect;
	private int round;
	
	//constructor
	
	public Development(String name, boolean permeff, boolean immeff, boolean speceff, SetOfValues cost) {
		super(name, permeff, immeff, speceff);
		this.cost = cost;
		// TODO Auto-generated constructor stub
	}
	
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
	
	//add the card to the right arraylist on the personal board
	public void setCardOnPersonalBoard(PersonalBoard personalBoard){
	} // redefined in every subclass

}
