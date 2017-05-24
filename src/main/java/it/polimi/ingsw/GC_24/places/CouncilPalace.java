package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class CouncilPalace {
	
	private ArrayList<CouncilPlace> councilPlaces;

	//constructor
	public CouncilPalace() {
		this.councilPlaces = new ArrayList<>();
	}
	
	
	//useful methods

	//empties all the places

	public void clearPlaces(){
		for(CouncilPlace councilPlace:this.councilPlaces){
			councilPlace.clearPlace();
		}
	}

	//getters and setters
	public ArrayList<CouncilPlace> getCouncilPlaces() {
		return councilPlaces;
	}

	public void setCouncilPlaces(ArrayList<CouncilPlace> councilPlaces) {
		this.councilPlaces = councilPlaces;
	}
	
}
