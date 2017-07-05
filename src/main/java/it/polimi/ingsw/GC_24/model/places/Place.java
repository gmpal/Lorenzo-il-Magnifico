package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;

public abstract class Place implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6111921142196116931L;
	private int costDice;
	protected boolean available;
	protected FamilyMember famMemberOnPlace;

	// constructor
	public Place(int costDice) {
		this.costDice = costDice;
		this.available = true;
		this.famMemberOnPlace = null;
	}

	// Useful methods

	/** clears the place from the Family Member*/
	public void clearPlace() {
	
		if (this.famMemberOnPlace != null) {
			this.famMemberOnPlace.setAvailable(true);
			this.setAvailable(true);
		}

		this.famMemberOnPlace = null;
	}




	// hashCode() and equals() redefined
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + costDice;
		result = prime * result + ((famMemberOnPlace == null) ? 0 : famMemberOnPlace.hashCode());
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
		return true;
	}


	// getters and setters
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

	public abstract ValueEffect getValue();

}
