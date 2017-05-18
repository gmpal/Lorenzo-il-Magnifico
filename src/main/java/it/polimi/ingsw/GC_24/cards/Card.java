package it.polimi.ingsw.GC_24.cards;

public class Card {
	
	private String name;
	private boolean permanentEffect; // this three boolean variables are
	private boolean immediateEffect; // used to check if these effects 
	private boolean specialEffect; // are present in the card
	
	public Card(String name,boolean permeff, boolean immeff, boolean speceff){
		this.name = name;
		this.permanentEffect = permeff;
		this.immediateEffect = immeff;
		this.specialEffect = speceff;
	}

	
}
