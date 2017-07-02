package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.model.dice.Die;
import it.polimi.ingsw.GC_24.model.dice.DieColour;

public class FamilyMember implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2479077189933957526L;
	private int memberValue;
	private boolean available;
	private DieColour memberColour;
	private boolean neutral;
	private PlayerColour playerColour;
	
	//constructors

	/**creates a family member with a value*/
	public FamilyMember(PlayerColour playerColour, Die die) {
		this.memberValue = die.getValue();
		this.available = true;
		this.memberColour = die.getColour();
		this.neutral = false;
		this.playerColour = playerColour;
	}
	
	/**creates a neutral family member*/
	public FamilyMember(PlayerColour playerColour) {
		this.memberValue = 0;
		this.available = true;
		this.memberColour = null;
		this.neutral=true;
		this.playerColour = playerColour;
	}
	
	
	/**sets a family member value and colour from a SetOfDice*/
	public void setMember(Die die) {
		this.setMemberColour(die.getColour());
		this.setMemberValue(die.getValue());
		this.setNeutral(false);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Status = "+isAvailableString()+ ", Value = " + memberValue );
		if (memberColour!=null){
			builder.append(", Colour = " + memberColour);
		}else {
			builder.append(", Colour = NEUTRAL");
		}
		return builder.toString();
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
	public PlayerColour getPlayerColour() {
		return playerColour;
	}
	public void setPlayerColour(PlayerColour playerColour) {
		this.playerColour = playerColour;
	}
	
}
