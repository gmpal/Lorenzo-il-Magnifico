package it.polimi.ingsw.GC_24.model.places;

import it.polimi.ingsw.GC_24.model.cards.Development;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;

public class TowerPlace extends Place {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2237068038161232570L;
	private Development correspondingCard;
	private ValueEffect value;
	private ImmediateEffect privilege;

	// constructor
	public TowerPlace(int costDice, ValueEffect value, ImmediateEffect privilege) {
		super(costDice);
		this.value = value;
		this.privilege = privilege;
		this.correspondingCard = null;
	}

	@Override
	public void clearPlace() {
		if (this.famMemberOnPlace != null) {
			this.famMemberOnPlace.setAvailable(true);
		}
		this.famMemberOnPlace = null;
		this.setAvailable(true);
		this.correspondingCard = null;
	}

	//getters and setters
	public Development getCorrespondingCard() {
		return correspondingCard;
	}

	public void setCorrespondingCard(Development correspondingCard) {
		this.correspondingCard = correspondingCard;
	}

	@Override
	public ValueEffect getValue() {
		return value;
	}

	public ImmediateEffect getPrivilege() {
		return privilege;
	}

	public void setPrivilege(ImmediateEffect privilege) {
		this.privilege = privilege;
	}

	public void setValue(ValueEffect value) {
		this.value = value;
	}

}
