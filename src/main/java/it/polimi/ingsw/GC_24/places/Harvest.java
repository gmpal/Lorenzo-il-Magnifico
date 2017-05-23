package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.PlayerColour;

public class Harvest {
	
	private ArrayList<HarvestPlace> harvestPlaces;

	//constructor
	public Harvest() {
		this.harvestPlaces = new ArrayList<>();
	}
	
	//empties all the places
	public void clearPlaces(){
		for(HarvestPlace harvestPlace:this.harvestPlaces){
			harvestPlace.clearPlace();
		}
	}
	
	//returns true if in the Harvest there already is a family member of the same colour as the parameter 
	public boolean isThereSameColour(PlayerColour playerColour){
		for(HarvestPlace harvestPlace:this.harvestPlaces){
			if(!harvestPlace.isAvailable()&&(harvestPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}
	
	//getter and setter
	public ArrayList<HarvestPlace> getHarvestPlaces() {
		return harvestPlaces;
	}

	public void setHarvestPlaces(ArrayList<HarvestPlace> harvestPlaces) {
		this.harvestPlaces = harvestPlaces;
	}
}
