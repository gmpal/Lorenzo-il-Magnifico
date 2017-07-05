package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;

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

	// getters and setters

	public ValueEffect getValue() {
		return value;
	}

	public void setValue(ValueEffect value) {
		this.value = value;
	}
	
	public ImmediateEffect getPrivilegeEffect() {
		return privilegeEffect;
	}

	public void setPrivilegeEffect(ImmediateEffect privilegeEffect) {
		this.privilegeEffect = privilegeEffect;
	}

}
