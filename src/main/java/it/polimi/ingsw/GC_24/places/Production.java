package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.PlayerColour;

public class Production {
	private ArrayList<ProductionPlace> productionPlaces;

	public Production() {
		this.productionPlaces = new ArrayList<>();
	}
	
	public void clearPlaces(){
		for(ProductionPlace productionPlace:this.productionPlaces){
			productionPlace.clearPlace();
		}
	}
	
	public boolean isThereSameColour(PlayerColour playerColour){
		for(ProductionPlace productionPlace:this.productionPlaces){
			if(!productionPlace.isAvailable()&&(productionPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
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

