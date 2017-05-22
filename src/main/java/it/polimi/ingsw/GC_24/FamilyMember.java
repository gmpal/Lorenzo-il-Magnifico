package it.polimi.ingsw.GC_24;

public class FamilyMember {

	private int memberValue;
	private boolean available;
	private DieColour memberColour;
	private boolean neutral;
	private Player player;
	
	
	//constructors
	//creates a valuated family member
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
	
	
	//Prints a family member
	@Override
	public String toString() {
		return "Value=" + memberValue + 
				", available=" + available + 
				", Colour=" + memberColour
				;
	}

	public String isAvailableString() {
		if (this.isAvailable()){ return "available";}
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
