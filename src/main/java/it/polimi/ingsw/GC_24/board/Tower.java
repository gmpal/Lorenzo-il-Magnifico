package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;


public class Tower extends Area {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4478838679204067326L;
	
	private static final int NUMTOWERPLACE=4;

	//constructor
	public Tower() {
		this.placesArray = createTower();
	}
	
	//inserts empty TowerPlaces in Tower
	public List<Place> createTower(){
		int value = 1;
		for(int num=0;num<NUMTOWERPLACE;num++){
			//TODO: caricare i valori da file 
			this.placesArray.add(new TowerPlace(value, null));
			value+=2;
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
