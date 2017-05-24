package it.polimi.ingsw.GC_24.effects;

public abstract class Effect {
	
	protected String name;
	
	//constructor
	public Effect(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Effect [name=" + name + "]";
	}
	
}
