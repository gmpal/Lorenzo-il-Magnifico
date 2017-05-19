package it.polimi.ingsw.GC_24;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class BonusTile {
	
	private SetOfValues harvestValues;
	private SetOfValues productionValues;
	
	//constructor
	public BonusTile(SetOfValues harvestValues, SetOfValues productionValues){
		this.harvestValues = harvestValues;
		this.productionValues = productionValues;
	}
	
	//getters e setters
	public SetOfValues getHarvestValues() {
		return harvestValues;
	}

	public void setHarvestValues(SetOfValues harvestValues) {
		this.harvestValues = harvestValues;
	}

	public SetOfValues getProductionValues() {
		return productionValues;
	}

	public void setProductionValues(SetOfValues productionValues) {
		this.productionValues = productionValues;
	}
	
	//add the harvestValues to the parameter
	public void giveHarvestValues(SetOfValues v){
		harvestValues.addTwoSetsOfValues(v);
	}
	
	//add the productionValues to the parameter
	public void giveProductiontValues(SetOfValues v){
		productionValues.addTwoSetsOfValues(v);
	}

}
