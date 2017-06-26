package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Player;

public class MarketPlace extends Place {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6828018041713667284L;
	private ValueEffect value;
	private ImmediateEffect privilegeEffect;

	// constructor
	public MarketPlace(ValueEffect value, ImmediateEffect privilegeEffect, int costDice) {
		super(costDice);
		
		this.value = value;
		this.privilegeEffect = privilegeEffect;
		this.available = true;
		this.famMemberOnPlace = null;
	}
/*
	@Override
	public void giveEffects(Player player) {
		player.setMyValues(this.getValue().getEffectValues().addTwoSetsOfValues(player.getMyValues()));
		player.setMyValues(this.value.getEffectValues().addTwoSetsOfValues(player.getMyValues()));
	}
*/
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "\nFree? " + isAvailable());
		if (isAvailable()){
			builder.append(" - You can get: ");
			if (value != null)
				builder.append(value);
			if (privilegeEffect != null)
				builder.append(privilegeEffect);
		}
		return builder.toString();
	}

	// getters and setters
	

	public ImmediateEffect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public ValueEffect getValue() {
		return value;
	}

	public void setValue(ValueEffect value) {
		this.value = value;
	}

	public void setPrivilegeEffect(ImmediateEffect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}

}
