package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.dice.Die;
import it.polimi.ingsw.GC_24.dice.DieColour;

public class FamilyMember {

	private int memberValue;
	private boolean available;
	private DieColour memberColour;
	private boolean neutral;
	private Player player;
	
	//constructors
	//creates a valued family member
	public FamilyMember(Player player, Die die) {
		this.memberValue = die.getValue();
		this.available = true;
		this.memberColour = die.getColour();
		this.neutral=false;
		this.player = player;
	}
	
	//creates a neutral family member
	public FamilyMember(Player player) {
		this.memberValue = 0;
		this.available = true;
		this.memberColour = null;
		this.neutral=true;
		this.player = player;
	}
	
	
	//sets a family member value and colour from a SetOfDice
	public void setMember(Die die) {
		this.setMemberColour(die.getColour());
		this.setMemberValue(die.getValue());
	}
	
	//Prints a family member
	@Override
	public String toString() {
		return "Value=" + memberValue + 
				"\tColour=" + memberColour+
				 "\t Status= "+isAvailableString();
	}

	
	public String isAvailableString(){
		if(this.isAvailable()){return "available";}
		else return "Not available";

	}
	
	
	//getters and setters
	public int getMemberValue() {
		return memberValue;
	}
	public void setMemberValue(int memberValue) {
		this.memberValue = memberValue;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public DieColour getMemberColour() {
		return memberColour;
	}
	public void setMemberColour(DieColour memberColour) {
		this.memberColour = memberColour;
	}
	public boolean isNeutral() {
		return neutral;
	}
	public void setNeutral(boolean neutral) {
		this.neutral = neutral;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
