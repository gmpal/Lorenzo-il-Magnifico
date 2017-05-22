package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.PlayerColour;

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
	
	public boolean isThereSameColour(PlayerColour playerColour){
		for(HarvestPlace harvestPlace:this.harvestPlaces){
			if(!harvestPlace.isAvailable()&&(harvestPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<HarvestPlace> getHarvestPlaces() {
		return harvestPlaces;
	}

	public void setHarvestPlaces(ArrayList<HarvestPlace> harvestPlaces) {
		this.harvestPlaces = harvestPlaces;
	}
}
