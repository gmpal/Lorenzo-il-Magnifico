package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Harvest {
	private ArrayList<HarvestPlace> harvestPlaces;

	public Harvest() {
		this.harvestPlaces = new ArrayList<HarvestPlace>();
	}
	
	public void clearPlaces(){
		for(HarvestPlace harvestPlace:this.harvestPlaces){
			harvestPlace.clearPlace();
		}
	}

	public ArrayList<HarvestPlace> getHarvestPlaces() {
		return harvestPlaces;
	}

	public void setHarvestPlaces(ArrayList<HarvestPlace> harvestPlaces) {
		this.harvestPlaces = harvestPlaces;
	}
}
