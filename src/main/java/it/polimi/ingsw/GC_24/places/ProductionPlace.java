package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class ProductionPlace extends Place{
	
	private int additionalCostDice;
	
	//constructor
	public ProductionPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);			
		this.additionalCostDice = additionalCostDice;
	}
	
	@Override
	public void giveEffects(){
		doProduction();
	}
	
	//gives the production value of the bonus tile to the player
	public void doProduction(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.getMyBoard().getBonusTile().giveProductiontValues(player.getMyValues());
	}
	
	//getter and setter
	public int getAdditionalCostDice() {
		return additionalCostDice;
	}

	public void setAdditionalCostDice(int additionalCostDice) {
		this.additionalCostDice = additionalCostDice;
	}	
	
}
