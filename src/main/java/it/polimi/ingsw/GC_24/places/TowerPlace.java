package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.values.Value;

public abstract class TowerPlace extends Place {
	
	public TowerPlace(int costDice, Value value){
		super(costDice, value);
	}
	
	public abstract void takeWithoutPlacing();
	
	public void giveEffects(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.setMyValues(this.value.addValueToSet(player.getMyValues())); 
	}
}
