package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class CouncilPlace extends Place {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3762534738065469265L;

	private ImmediateEffect privilegeEffect;

	// constructor
	public CouncilPlace(int costDice, Value value, ImmediateEffect privilegeEffect) {
		super(costDice, value);
		this.privilegeEffect = privilegeEffect;
	}

	@Override
	public void giveEffects(Player player) {
		player.setMyValues(this.getValue().addValueToSet(player.getMyValues()));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Free? " + isAvailable());
		if (isAvailable()){
			builder.append(" - You get: " + getValue() + " and a Council Privilege" + privilegeEffect);
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

}
