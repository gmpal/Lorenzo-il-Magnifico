package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Player;

public class CouncilPlace extends Place {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6096762163130360101L;
	private ImmediateEffect privilegeEffect;
	private ValueEffect value;

	// constructor
	public CouncilPlace(int costDice, ValueEffect value, ImmediateEffect privilegeEffect) {
		super(costDice);
		this.value=value;
		this.privilegeEffect = privilegeEffect;
	}

	@Override
	public void giveEffects(Player player) {
		player.setMyValues(this.value.getEffectValues().addTwoSetsOfValues(player.getMyValues()));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "\nFree? " + isAvailable());
		if (isAvailable()){
			builder.append(" - You get: " + getValue() + " and a Council Privilege");
		}
		return builder.toString();
	}

	// getter and setter
	public ImmediateEffect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(ImmediateEffect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}

	@Override
	public ValueEffect getValue() {
		return value;
	}

	public void setValue(ValueEffect value) {
		this.value = value;
	}
	

}
