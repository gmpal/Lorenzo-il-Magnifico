package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public abstract class Place { 
	
	private int costDice; 
	protected boolean available;
	protected FamilyMember famMemberOnPlace;
	private Value value;
	
	//constructor
	public Place(int costDice, Value value){
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
	public abstract void giveEffects(Player player); 
	
	
	//hashCode() and equals() redefined
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + costDice;
		result = prime * result + ((famMemberOnPlace == null) ? 0 : famMemberOnPlace.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (available != other.available)
			return false;
		if (costDice != other.costDice)
			return false;
		if (famMemberOnPlace == null) {
			if (other.famMemberOnPlace != null)
				return false;
		} else if (!famMemberOnPlace.equals(other.famMemberOnPlace))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
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
