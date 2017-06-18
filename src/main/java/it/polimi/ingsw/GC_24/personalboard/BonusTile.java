package it.polimi.ingsw.GC_24.personalboard;

import it.polimi.ingsw.GC_24.values.SetOfValues;

public class BonusTile implements java.io.Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1103689384999543201L;
	
	private SetOfValues harvestValues;
	private SetOfValues productionValues;
	
	//constructor
	public BonusTile(SetOfValues harvestValues, SetOfValues productionValues){
		this.harvestValues = harvestValues;
		this.productionValues = productionValues;
	}
	
	//adds the harvestValues to the parameter
	public void giveHarvestValues(SetOfValues v){
		harvestValues.addTwoSetsOfValues(v);
	}
		
	//adds the productionValues to the parameter
	public void giveProductiontValues(SetOfValues v){
		productionValues.addTwoSetsOfValues(v);
	}
	
	@Override
	public String toString() {
		return "HarvestValues=" + harvestValues + ", ProductionValues=" + productionValues;
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
	
}
