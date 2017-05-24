package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.values.Value;

public abstract class Place {
	
	protected int costDice; 
	protected boolean available;
	protected FamilyMember famMemberOnPlace;
	protected Value value;
	
	//constructor
	public Place(int costDice, Value value ){
		this.costDice = costDice;
		this.available = true;
		this.famMemberOnPlace = null;
		this.value = value;
	}
	
	//Useful methods
	
	//clears the place from the Family Member
	public void clearPlace(){
		this.famMemberOnPlace = null;
		this.setAvailable(true);
	}
	
	//redefined in MarketPlace, Tower, CouncilPlace change parameter in FamilyMember	
	public abstract void giveEffects(); 
	
	@Override
	public String toString() {
		return " - costDice=" + costDice + ", available=" + available + ", "
				+ "famMemberOnPlace=" + famMemberOnPlace + ", value=" + value;
	}

	//getters and setters
	public int getCostDice() {
		return costDice;
	}
	public void setCostDice(int costDice) {
		this.costDice = costDice;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public FamilyMember getFamMemberOnPlace() {
		return famMemberOnPlace;
	}
	public void setFamMemberOnPlace(FamilyMember famMemberOnPlace) {
		this.famMemberOnPlace = famMemberOnPlace;
		this.setAvailable(false);
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}	
	
}
