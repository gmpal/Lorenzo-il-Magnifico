package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends Place{
	
	private int additionalCostDice;

	//constructor
	public HarvestPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);
		this.additionalCostDice = additionalCostDice;
	}
	
	@Override
	public void giveEffects(){
		doHarvest();
	}
	
	//gives the production value of the bonus tile to the player
	public void doHarvest(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}
	
	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}
	
}
	
	


