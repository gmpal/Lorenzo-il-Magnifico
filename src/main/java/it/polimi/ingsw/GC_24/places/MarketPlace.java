package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class MarketPlace extends Place {

	private Value extraValue;
	private Effect privilegeEffect;
	
	//constructor
	public MarketPlace(Value extraValue, Value value, Effect privilegeEffect, int costDice){
		super(costDice, value);
		this.extraValue=extraValue;
		this.privilegeEffect=privilegeEffect;
		this.available=true;
		this.famMemberOnPlace=null;
	}
	
	@Override
	public void giveEffects(Player player){
		player.setMyValues(this.getValue().addValueToSet(player.getMyValues()));
		player.setMyValues(this.extraValue.addValueToSet(player.getMyValues()));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "MarketPlace free: " + isAvailable());
		if (isAvailable()){
			builder.append("You can get: ");
			if (getValue() != null)
				builder.append(getValue());
			if (extraValue != null)
				builder.append(extraValue);
			if (privilegeEffect != null)
				builder.append(privilegeEffect);
		}
		return builder.toString();
	}

	//getters and setters
	public Value getExtraValue() {
		return extraValue;
	}

	public void setExtraValue(Value extraValue) {
		this.extraValue = extraValue;
	}

	public Effect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(Effect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}
	
	
}
