package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Market {
	
	private ArrayList<MarketPlace> marketArray;
	private boolean placesLocked;
	private static final int MINPLACES=2;				//Place with 2 players
	private static final int MAXPLACES=4;				//Place from 3 players
	private static final int COSTDICE=1;				
	
	//constructor
	public Market(boolean placesLocked) {
		this.placesLocked=placesLocked;
		this.marketArray = createMarket();
	}
	
	public ArrayList<MarketPlace> createMarket(){
		int numPlaces;
		
		if(this.placesLocked){
			numPlaces=MINPLACES;
		}
		else numPlaces=MAXPLACES;
		
		for(int num=0;num<numPlaces;num++){
			marketArray.add(new MarketPlace(null, null, null, COSTDICE));
		}
		return marketArray;
	}
	
	//empties all the places
	public void clearPlaces(){
		for(MarketPlace marketplace:this.marketArray){
			marketplace.clearPlace();
		}
	}
	
	//getter and setter
	public ArrayList<MarketPlace> getMarketPlaces() {
		return marketArray;
	}

	public void setMarketPlaces(ArrayList<MarketPlace> marketPlaces) {
		this.marketArray = marketPlaces;
	}
}
