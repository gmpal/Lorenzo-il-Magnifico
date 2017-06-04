package it.polimi.ingsw.GC_24.board;

import java.util.*;

import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.ProductionPlace;

public class Production extends Area {
	
	private boolean placesLocked;
	private int numPlayers;
	private static final int FACTOR=2; 						//Max number of player's familyMember in Production
	private static final int MINPLACES=1;					//Number of ProductionPlaces with 2 players
	private static final int COSTDICE=1;					//Cost of first place
	private static final int ADDITIONALCOST=0;				//Additional Cost dice on the first place
	private static final int ADDITIONALCOST2=3;				//Additional Cost dice from the second place
	
	//constructor
	public Production(boolean placesLocked, int numPlayers) {
		this.placesLocked=placesLocked;
		this.numPlayers=numPlayers;
		this.placesArray = createProduction();
	}
	
	//inserts empty ProductionPlaces in Production
	public List<Place> createProduction(){
		int numProducionPlaces;	
		
		if(this.placesLocked){
			numProducionPlaces=MINPLACES;
		}
		else numProducionPlaces=numPlayers*FACTOR;
			
		for(int num=0;num<numProducionPlaces;num++){
			if(num==0){
				this.placesArray.add(new ProductionPlace(COSTDICE, null, ADDITIONALCOST));
			}
			else this.placesArray.add(new ProductionPlace(COSTDICE, null, ADDITIONALCOST2));
		}
		return placesArray;
	} 

}
