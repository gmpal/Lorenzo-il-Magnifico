package it.polimi.ingsw.GC_24.board;

import java.util.*;

import it.polimi.ingsw.GC_24.places.MarketPlace;
import it.polimi.ingsw.GC_24.places.Place;

public class Market extends Area {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2530623420397285959L;
	
	private boolean placesLocked;
	private static final int MINPLACES=2;				//Place with 2 players
	private static final int MAXPLACES=4;				//Place from 3 players
	private static final int COSTDICE=1;				
	
	//constructor
	public Market(boolean placesLocked) {
		this.placesLocked = placesLocked;
		this.placesArray = createMarket();
	}
	
	public List<Place> createMarket(){
		int numPlaces;
		
		if(this.placesLocked){
			numPlaces = MINPLACES;
		}
		else numPlaces = MAXPLACES;
		
		for(int num=0;num<numPlaces;num++){
			placesArray.add(new MarketPlace(null, null, null, COSTDICE));
		}
		return placesArray;
	}
	
}
