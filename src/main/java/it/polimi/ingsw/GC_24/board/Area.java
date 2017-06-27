package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Area implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2756356223377195910L;
	protected List<Place> placesArray = new ArrayList<>();
	
	/**This method returns true if in the zone you're trying to put your family member in
	 * there's already a family member of the same Player. If you're trying to put a neutral
	 * family member, it directly returns false*/
	public boolean isThereSameColour(FamilyMember familyMember) {
		PlayerColour colour = familyMember.getPlayerColour();
		if (familyMember.isNeutral()) {
			System.out.println("ma sono neutro e non lo sapevo?");

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
	
	/** returns true if a family member is already on a place in the placesArray
	* I have to pay 3 coins to place a familiar*/
	public boolean isOccupied () {
		for (Place place : this.placesArray) {
			if (!place.isAvailable()){
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
	/**This methods returns the corresponding space if the value is different than zero,
	 * else it returns the first empty space available*/
	public Place getPlaceFromStringOrFirstIfZero(String place){
		if (!place.equals("0")){
			int i = Integer.parseInt(place);
			if (placesArray.size() < i){
				return null;
			}
			else return placesArray.get(i-1);
		}else{ 		
			return this.getFirstEmptyPlace();}
		
	}
	
	public Place getFirstEmptyPlace(){
		for (Place p: this.placesArray){
			if(p.isAvailable()){
				return p;
			}
		}
		return null;
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
	
	@Override
	public String toString() {
		return placesArray.toString();
	} 

	//getter and setter
	public List<Place> getPlacesArray() {
		return placesArray;
	}

	public void setPlacesArray(List<Place> placesArray) {
		this.placesArray = placesArray;
	}
}
