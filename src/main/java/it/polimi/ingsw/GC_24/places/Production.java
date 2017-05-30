package it.polimi.ingsw.GC_24.places;

import java.util.*;
import it.polimi.ingsw.GC_24.model.PlayerColour;

public class Production {
	
	private List<ProductionPlace> productionArray = new ArrayList<>();
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
		this.productionArray = createProduction();
	}
	
	//inserts empty ProductionPlaces in Production
	public List<ProductionPlace> createProduction(){
		int numProducionPlaces;	
		
		if(this.placesLocked){
			numProducionPlaces=MINPLACES;
		}
		else numProducionPlaces=numPlayers*FACTOR;
			
		for(int num=0;num<numProducionPlaces;num++){
			if(num==0){
				this.productionArray.add(new ProductionPlace(COSTDICE, null, ADDITIONALCOST));
			}
			else this.productionArray.add(new ProductionPlace(COSTDICE, null, ADDITIONALCOST2));
		}
		return productionArray;
	} 
	
	//empties all the places
	public void clearPlaces(){
		for(ProductionPlace productionPlace:this.productionArray){
			productionPlace.clearPlace();
		}
	}
	
	//returns true if in the Production there already is a family member of the same colour as the parameter 
	public boolean isThereSameColour(PlayerColour playerColour){
		for(ProductionPlace productionPlace:this.productionArray){
			if(!productionPlace.isAvailable()&&(productionPlace.getFamMemberOnPlace().getPlayer().getMyColour()).equals(playerColour)){
				return true;
			}
		}
		return false;
	}

	//getter and setter
	public List<ProductionPlace> getProductionPlaces() {
		return productionArray;
	}

	public void setProductionPlaces(List<ProductionPlace> productionPlaces) {
		this.productionArray = productionPlaces;
	}
	
	
}

