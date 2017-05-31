package it.polimi.ingsw.GC_24.places;

import java.util.*;

import it.polimi.ingsw.GC_24.model.PlayerColour;

public class Harvest {
	
	private List<HarvestPlace> harvestArray = new ArrayList<>();
	private boolean placesLocked;
	private int numPlayers;
	private static final int FACTOR=2; 						//Max number of player's familyMember in Production
	private static final int MINPLACES=1;					//Number of ProductionPlaces with 2 players
	private static final int COSTDICE=1;					//Cost of first place
	private static final int ADDITIONALCOST=0;				//Additional Cost dice on the first place
	private static final int ADDITIONALCOST2=3;				//Additional Cost dice from the second place

	//constructor
	public Harvest(boolean placesLocked, int numPlayers) {
		this.placesLocked=placesLocked;
		this.numPlayers=numPlayers;
		this.harvestArray = createHarvest();
	}
	
	//inserts empty ProductionPlaces in Production
	public List<HarvestPlace> createHarvest(){
		int numProducionPlaces;	
		if(this.placesLocked){
			numProducionPlaces=MINPLACES;
		}
		else numProducionPlaces=numPlayers*FACTOR;
				
		for(int num=0;num<numProducionPlaces;num++){
			if(num==0){
				this.harvestArray.add(new HarvestPlace(COSTDICE, null, ADDITIONALCOST));
			}
			else this.harvestArray.add(new HarvestPlace(COSTDICE, null, ADDITIONALCOST2));
		}
		return harvestArray;
	} 
	
	//empties all the places
	public void clearPlaces(){
		for(HarvestPlace harvestPlace:this.harvestArray){
			harvestPlace.clearPlace();
		}
	}
	
	//returns true if in the Harvest there already is a family member of the same colour as the parameter 
	public boolean isThereSameColour(PlayerColour playerColour){
		for(HarvestPlace harvestPlace:this.harvestArray){
			if(!harvestPlace.isAvailable()&&(harvestPlace.getFamMemberOnPlace().getPlayerColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}
	
	//getter and setter
	public List<HarvestPlace> getHarvestPlaces() {
		return harvestArray;
	}

	public void setHarvestPlaces(List<HarvestPlace> harvestPlaces) {
		this.harvestArray = harvestPlaces;
	}
}
