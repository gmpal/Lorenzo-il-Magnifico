package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class CouncilPlace extends Place {
	
	private Effect privilegeEffect;

	//constructor
	public CouncilPlace(int costDice, Value value,Effect privilegeEffect) {
		super(costDice, value);
		this.privilegeEffect = privilegeEffect;
	}

	@Override
	public void giveEffects(Player player){
		player.setMyValues(this.getValue().addValueToSet(player.getMyValues()));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "CouncilPlace free: " + isAvailable());
		if (isAvailable()){
			builder.append("You get: " + getValue() + ", " + privilegeEffect);
		}
		return builder.toString();
	}
	
	//getter and setter
	public Effect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(Effect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}
	
}
