package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.*;

public abstract class Moltiplication extends ImmediateEffect{
	
	protected Value value;
	protected PersonalBoard playersBoard;
	
	//constructor
	public Moltiplication(String name, SetOfValues effectValues, PersonalBoard playersBoard, Value value) {
		super(name, effectValues);
		// TODO Auto-generated constructor stub
		this.value = value;
		this.playersBoard = playersBoard;
	}
	
	//gives the effect to the player
	public abstract void moltiplicationEffect(); //redefined for every subclass

}
