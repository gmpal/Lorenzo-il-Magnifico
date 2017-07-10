package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.places.TowerPlace;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * This effects lets the player choose another card of ONE or MULTI colours,
 * with a specific minimum value. It's exactly like putting a familyMember: the
 * player has to pay 3 coins if the tower is occupied, receives the
 * immediateEffect if the place has it, can increase the value with servants
 */
public class ChooseNewCard extends ImmediateEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7160788868490219138L;

	private int dieValue;
	private String type;
	private SetOfValues setOfValue;
	private TowerPlace towerPlace;

	public ChooseNewCard(String name, String type, int dieValue, SetOfValues setOfValue) {
		super(name);
		this.type = type;
		this.dieValue = dieValue;
		this.setSetOfValue(setOfValue);
	}

	// useful methods

	public void assignParameters(TowerPlace towerPlace) {
		setTowerPlace(towerPlace);
	}

	@Override
	public void giveImmediateEffect(Player player) {
		// This method is handled differently and doesn't do anything
		;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Choose New Card:");
		if (type != null) {
			builder.append(" You can take a " + type + " card with die value " + dieValue);
		} else
			builder.append(" You can take a card of any type with die value " + dieValue);
		if (setOfValue != null) {
			builder.append(" and you have an extra discount on the card's price of " + setOfValue);
		}
		return builder.toString();
	}

	/*
	 * Not necessary methods, but better than choosing with if... CHOOSE NEW CARD
	 * EFFECT IS HANDLED DIFFERENTLY
	 */
	@Override
	public String generateParametersRequest() {
		if (type == null) {
			return "everyTower";
		} else
			return type;
	}

	@Override
	public HashMap<String, Object> generateHashMapToSend(String response) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("chooseNewCard", response);
		return hashMap;
	}

	// getters and setters
	public int getDieValue() {
		return dieValue;
	}

	public void setDieValue(int dieValue) {
		this.dieValue = dieValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SetOfValues getSetOfValue() {
		return setOfValue;
	}

	public void setSetOfValue(SetOfValues setOfValue) {
		this.setOfValue = setOfValue;
	}

	public TowerPlace getTowerPlace() {
		return towerPlace;
	}

	public void setTowerPlace(TowerPlace towerPlace) {
		this.towerPlace = towerPlace;
	}
}