package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class MarketPlace extends Place {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5096841999224669924L;

	private Value extraValue;
	private ImmediateEffect privilegeEffect;

	// constructor
	public MarketPlace(Value extraValue, Value value, ImmediateEffect privilegeEffect, int costDice) {
		super(costDice, value);
		this.extraValue = extraValue;
		this.privilegeEffect = privilegeEffect;
		this.available = true;
		this.famMemberOnPlace = null;
	}

	@Override
	public void giveEffects(Player player) {
		player.setMyValues(this.getValue().addValueToSet(player.getMyValues()));
		player.setMyValues(this.extraValue.addValueToSet(player.getMyValues()));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Free? " + isAvailable());
		if (isAvailable()){
			builder.append(" - You can get: ");
			if (getValue() != null)
				builder.append(getValue());
			if (extraValue != null)
				builder.append(extraValue);
			if (privilegeEffect != null)
				builder.append(privilegeEffect);
		}
		return builder.toString();
	}

	// getters and setters
	public Value getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(Value extraValue) {
		this.extraValue = extraValue;
	}

	public ImmediateEffect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(ImmediateEffect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}

}
