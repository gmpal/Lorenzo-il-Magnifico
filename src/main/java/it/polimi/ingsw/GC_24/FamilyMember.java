package it.polimi.ingsw.GC_24;

public class FamilyMember {
	
	private int memberValue;
	private boolean available;
	private DieColour memberColour;
	private PlayerColour playerColour;
	
	
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
	public PlayerColour getPlayerColour() {
		return playerColour;
	}
	public void setPlayerColour(PlayerColour playerColour) {
		this.playerColour = playerColour;
	}
	
	
	
}
