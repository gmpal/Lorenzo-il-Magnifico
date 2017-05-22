package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;

import it.polimi.ingsw.GC_24.PlayerColour;

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
	
	public boolean isThereSameColour(PlayerColour playerColour){
		for(ProductionPlace productionPlace:this.productionPlaces){
			if(productionPlace.getFamMemberOnPlace().getMemberColour().equals(playerColour)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<ProductionPlace> getProductionPlaces() {
		return productionPlaces;
	}

	public void setProductionPlaces(ArrayList<ProductionPlace> productionPlaces) {
		this.productionPlaces = productionPlaces;
	}
	
	
}

