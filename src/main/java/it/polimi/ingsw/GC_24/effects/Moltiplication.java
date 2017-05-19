package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.values.*;

public class Moltiplication extends ImmediateEffect{
	
	protected Value value;
	protected PersonalBoard playersBoard;
	
	//constructor
	public Moltiplication(String name, SetOfValues effectValues,Value value) {
		super(name, effectValues);
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	//
	public void moltiplicationEffect(){
	}

}
