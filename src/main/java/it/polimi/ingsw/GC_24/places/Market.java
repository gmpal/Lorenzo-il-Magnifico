package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Market {
	private ArrayList<MarketPlace> marketPlaces;

	public Market() {
		this.marketPlaces = new ArrayList<MarketPlace>();
	}

	public ArrayList<MarketPlace> getMarketPlaces() {
		return marketPlaces;
	}

	public void setMarketPlaces(ArrayList<MarketPlace> marketPlaces) {
		this.marketPlaces = marketPlaces;
	}
}
