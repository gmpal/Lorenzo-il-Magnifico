package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;


public class Tower extends Area {
	
	private static final int NUMTOWERPLACE=4;
	
	//constructor
	public Tower() {
		this.placesArray = createTower();
	}
	
	//inserts empty TowerPlaces in Tower
	public List<Place> createTower(){
		for(int num=0;num<NUMTOWERPLACE;num++){
			this.placesArray.add(new TowerPlace(0, null));
		}
		return placesArray;
	}

	// return true if a family member is already on a place in the Tower
	public boolean isTowerOccupied() {
		for (Place towerPlace : this.placesArray) {
			if (!towerPlace.isAvailable()) {
				return true;
			}
		}
		return false;
	}

}