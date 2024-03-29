package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;

import it.polimi.ingsw.GC_24.model.Player;

/**
 * this type of effect lets the player perform a production or a harvest without
 * placing a family member, setting a starting value that can be increased with
 * servants
 */
public abstract class PerformActivity extends ImmediateEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2475182339045729171L;

	protected int dieValue;

	protected int incrementServants;

	// constructor
	public PerformActivity(String name, int dieValue) {
		super(name);
		this.dieValue = dieValue;
	}

	@Override
	public abstract void giveImmediateEffect(Player player);

	// getter and setter
	public int getDieValue() {
		return dieValue;
	}

	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}

	@Override
	public void assignParameters(String response) {
		this.incrementServants = Integer.parseInt(response);
	}

	@Override
	public HashMap<String, Object> generateHashMapToSend(String response) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("activityParamRequest", response);
		return map;
	}
}