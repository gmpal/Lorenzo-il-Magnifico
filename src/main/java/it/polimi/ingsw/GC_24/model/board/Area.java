package it.polimi.ingsw.GC_24.model.board;

import java.util.*;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.places.Place;

public abstract class Area implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2756356223377195910L;
	protected ArrayList<Place> placesArray = new ArrayList<>();
	
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
			if (place.getFamMemberOnPlace()!=null&&!place.isAvailable() && !place.getFamMemberOnPlace().isNeutral()
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
		System.out.println("Controller --> (Dentro getPlaceFromStringOrFirstIfZero) Controllo il place che mi hai inviato ");
		if (!place.equals("0")){

			System.out.println("Controller --> (Dentro getPlaceFromStringOrFirstIfZero) Il place è diverso da 0");

			int i = Integer.parseInt(place);
			System.out.println("Controller --> (Dentro getPlaceFromStringOrFirstIfZero) Place trasformato in un intero");
			if (placesArray.size() < i){

				System.out.println("Controller --> (Dentro getPlaceFromStringOrFirstIfZero) l'array non è abbastanza grande per il posto scelto da te ");
				return null;
			}
			else {
				System.out.println("Controller --> (Dentro getPlaceFromStringOrFirstIfZero) La tua richiesta è adeguata, ritorno il posto corrispondente");

				return placesArray.get(i-1);
			}
		}else{ 		
			System.out.println("Controller -->(Dentro getPlaceFromStringOrFirstIfZero) Ricevuto un posto nullo, quindi vuoi il primo posto libero");
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
		this.placesArray = (ArrayList<Place>) placesArray;
	}
}
