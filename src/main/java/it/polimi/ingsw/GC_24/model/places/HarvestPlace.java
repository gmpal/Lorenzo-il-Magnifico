package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;

public class HarvestPlace extends ActivityPlace{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1219130341018758993L;

	// constructor
	public HarvestPlace(int costDice, int additionalCostDice) {
		super(costDice, additionalCostDice);
	}

	
	/*public void doActivity(Player player){
	player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	List<Development> harvestCards = player.getMyBoard().getPersonalTerritories().getCards();
	for (Development  card:harvestCards){
		Territories territory = (Territories) card;
		if (territory.getDieValueHarvest() <= (getFamMemberOnPlace().getMemberValue() + getAdditionalCostDice())){
			territory.getImmediateEffect1().giveImmediateEffect(player);
			territory.getImmediateEffect().giveImmediateEffect(player);
			} 
		}
	}*/

	@Override
	public ValueEffect getValue() {
		//returns the place value, but harvest place doesn't have one
		return null;
	}
}
