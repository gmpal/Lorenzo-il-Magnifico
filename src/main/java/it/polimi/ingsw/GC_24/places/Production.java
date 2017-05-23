package it.polimi.ingsw.GC_24.places;

import java.util.ArrayList;
import it.polimi.ingsw.GC_24.PlayerColour;

public class Production {
	
	private ArrayList<ProductionPlace> productionPlaces;

	//constructor
	public Production() {
		this.productionPlaces = new ArrayList<>();
	}
	
	//empties all the places
	public void clearPlaces(){
		for(ProductionPlace productionPlace:this.productionPlaces){
			productionPlace.clearPlace();
		}
	}
	
	//returns true if in the Production there already is a family member of the same colour as the parameter 
	public boolean isThereSameColour(PlayerColour playerColour){
		for(ProductionPlace productionPlace:this.productionPlaces){
			if(!productionPlace.isAvailable()&&(productionPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}

	//getter and setter
	public ArrayList<ProductionPlace> getProductionPlaces() {
		return productionPlaces;
	}

	public void setProductionPlaces(ArrayList<ProductionPlace> productionPlaces) {
		this.productionPlaces = productionPlaces;
	}
	
	
}

