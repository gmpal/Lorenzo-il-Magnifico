package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.places.HarvestPlace;
import it.polimi.ingsw.GC_24.places.Place;

public class Harvest extends Area {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7173055480132725602L;
	
//	private List<HarvestPlace> harvestArray = new ArrayList<>();
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
		placesArray = createHarvest();
	}
	
	//inserts empty ProductionPlaces in Production
	public List<Place> createHarvest(){
		int numProducionPlaces;	
		if(this.placesLocked){
			numProducionPlaces=MINPLACES;
		}
		else numProducionPlaces=numPlayers*FACTOR;
				
		for(int num=0;num<numProducionPlaces;num++){
			if(num==0){
				this.placesArray.add(new HarvestPlace(COSTDICE, null, ADDITIONALCOST));
			}
			else this.placesArray.add(new HarvestPlace(COSTDICE, null, ADDITIONALCOST2));
		}
		return placesArray;
	}

}
