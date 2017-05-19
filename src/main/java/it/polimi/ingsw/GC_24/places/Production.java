package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

public class Production {
	private ArrayList<ProductionPlace> productionPlaces;

	public Production() {
		this.productionPlaces = new ArrayList<ProductionPlace>();
	}
	
	public void clearPlaces(){
		for(ProductionPlace productionPlace:this.productionPlaces){
			productionPlace.clearPlace();
		}
	}

	public ArrayList<ProductionPlace> getProductionPlaces() {
		return productionPlaces;
	}

	public void setProductionPlaces(ArrayList<ProductionPlace> productionPlaces) {
		this.productionPlaces = productionPlaces;
	}
	
	
}

