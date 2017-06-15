package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.TowerPlace;
import it.polimi.ingsw.GC_24.values.SetOfValues;

/*This effects lets the player choose another card of ONE or MULTI colours, with a specific minimum value.
 * It's exactly like putting a familyMember: the player has to pay 3 coins if the tower is occupied,
 * receives the immediateEffect if the place has it, can increase the value with servants */
public class ChooseNewCard extends ImmediateEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7160788868490219138L;

	private int dieValue;
	private String type;
	private SetOfValues setOfValue;

	
	public ChooseNewCard(String name, String type, int dieValue, SetOfValues setOfValue) {
		super(name);
		this.type=type;
		this.dieValue = dieValue;
		this.setSetOfValue(setOfValue);
	}

	//getters and setters
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
	
	@Override
	public void giveImmediateEffect(Player player) {
		
	}
	
	public void chooseCard(Player player, TowerPlace towerPlace){
		towerPlace.takeWithoutPlacing(player);
	}
}
