package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Market {
	
	private ArrayList<MarketPlace> marketPlaces;

	//constructor
	public Market() {
		this.marketPlaces = new ArrayList<>();
	}
	
	//empties all the places
	public void clearPlaces(){
		for(MarketPlace marketplace:this.marketPlaces){
			marketplace.clearPlace();
		}
	}
	
	//getter and setter
	public ArrayList<MarketPlace> getMarketPlaces() {
		return marketPlaces;
	}

	public void setMarketPlaces(ArrayList<MarketPlace> marketPlaces) {
		this.marketPlaces = marketPlaces;
	}
}
