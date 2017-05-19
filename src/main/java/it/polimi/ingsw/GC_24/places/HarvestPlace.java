package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.FamilyMember;
import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.personalboard.PersonalBoard;
import it.polimi.ingsw.GC_24.values.Value;

public class HarvestPlace extends Place{
	private int additionalCostDice;

	public HarvestPlace(int costDice, Value value, int additionalCostDice) {
		super(costDice, value);
		this.additionalCostDice = additionalCostDice;
	}
	
	@Override
	public void giveEffects(){
		doHarvest();
	}
	
	public void doHarvest(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}
}
	
	


