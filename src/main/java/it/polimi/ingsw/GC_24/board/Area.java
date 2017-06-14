package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Area implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186597780187256338L;

	protected List<Place> placesArray = new ArrayList<>();
	
	// returns true if in the same Tower there already is a family member of the
	// same colour as the parameter
	public boolean isThereSameColour(FamilyMember familyMember) {
		PlayerColour colour = familyMember.getPlayerColour();
		if (familyMember.isNeutral()) {
			return false;
		}
		for (Place place : this.placesArray) {
			if (!place.isAvailable() && !place.getFamMemberOnPlace().isNeutral()
				&& (place.getFamMemberOnPlace().getPlayerColour()).equals(colour)){
				return true;
			}
		}
		return false;
	}
	
	// empties all the places
	public void clearPlaces() {
		for (Place place : this.placesArray) {
			place.clearPlace();
		}
	}

	//hashCode() and equals() redefined
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placesArray == null) ? 0 : placesArray.hashCode());
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
		Area other = (Area) obj;
		if (placesArray == null) {
			if (other.placesArray != null)
				return false;
		} else if (!placesArray.equals(other.placesArray))
			return false;
		return true;
	}

	//getter and setter
	public List<Place> getPlacesArray() {
		return placesArray;
	}

	public void setPlacesArray(List<Place> placesArray) {
		this.placesArray = placesArray;
	}
}
