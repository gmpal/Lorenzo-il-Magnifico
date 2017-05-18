package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.values.Value;

public class CouncilPlace extends Place {
	private Effect privilegeEffect;

	public CouncilPlace(int costDice, Value value,Effect privilegeEffect) {
		super(costDice, value);
		this.privilegeEffect = privilegeEffect;
	}

	public Effect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(Effect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}
	
	@Override
	public void giveEffects(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.setMyValues(this.value.addValueToSet(player.getMyValues()));
	}
}
