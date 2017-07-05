package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.ValueEffect;

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
