package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.values.Servant;

/**
 * this effect is a malus that can be activated after the Vatican
 * excommunication. The player will need more than one servant to increase their
 * family member's value by one
 */
public class ChangeServantsValue extends PermanentEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2169724349163367769L;
	private int servantsQuantity;

	// constructor
	public ChangeServantsValue(String name, int servantsValue) {
		super(name);
		this.servantsQuantity = servantsValue;
	}

	public void changeServantsValue(Servant servants) {
		servants.setQuantity((int) (servants.getQuantity() / servantsQuantity));
	}

	// getters and setters
	public int getServantsQuantity() {
		return servantsQuantity;
	}

	public void setServantsQuantity(int servantsQuantity) {
		this.servantsQuantity = servantsQuantity;
	}
}
