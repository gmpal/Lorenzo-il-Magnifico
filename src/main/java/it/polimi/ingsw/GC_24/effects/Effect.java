package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.PlayerColour;

public abstract class Effect {
	
	protected String name;
	protected PlayerColour player;

	public Effect(String name){
		this.name = name;
	}
}
