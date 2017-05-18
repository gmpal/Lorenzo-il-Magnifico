package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.Player;
import it.polimi.ingsw.GC_24.effects.Effect;
import it.polimi.ingsw.GC_24.values.Value;

public class MarketPlace extends Place {

	private Value extraValue;
	private Effect privilegeEffect;
	
	public MarketPlace(Value extraValue, Value value, Effect privilegeEffect, int costDice){
		super(costDice, value);
		this.extraValue=extraValue;
		this.privilegeEffect=privilegeEffect;
		this.available=true;
		this.famMemberOnPlace=null;
	}
	
	@Override
	public void giveEffects(){
		Player player=this.famMemberOnPlace.getPlayer();
		player.setMyValues(this.value.addValueToSet(player.getMyValues()));
		player.setMyValues(this.extraValue.addValueToSet(player.getMyValues()));
	}

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
