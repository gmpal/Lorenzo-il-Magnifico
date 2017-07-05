package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;

public class ProductionPlace extends ActivityPlace{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1488467430023982193L;

	//constructor
	public ProductionPlace(int costDice, int additionalCostDice) {
		super(costDice, additionalCostDice);
	}
	
	@Override
	public ValueEffect getValue() {
		return null;
	}
}
