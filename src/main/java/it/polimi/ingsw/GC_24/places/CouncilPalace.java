package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class CouncilPalace {
	private ArrayList<CouncilPlace> councilPlaces;

	public CouncilPalace() {
		this.councilPlaces = new ArrayList<CouncilPlace>();
	}
	
	public void clearPlaces(){
		for(CouncilPlace councilPlace:this.councilPlaces){
			councilPlace.clearPlace();
		}
	}

	public ArrayList<CouncilPlace> getCouncilPlaces() {
		return councilPlaces;
	}

	public void setCouncilPlaces(ArrayList<CouncilPlace> councilPlaces) {
		this.councilPlaces = councilPlaces;
	}
	
	

	
}
