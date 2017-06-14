package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.model.Player;

/*This effects lets the player choose another card of ONE or MULTI colours, with a specific minimum value.
 * It's exactly like putting a familyMember: the player has to pay 3 coins if the tower is occupied,
 * receives the immediateEffect if the place has it, can increase the value with servants */
public class ChooseNewCard extends ImmediateEffect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7160788868490219138L;

	private Development type;
	private int dieValue;
	
	public ChooseNewCard(String name, Development type, int dieValue) {
		super(name);
		this.dieValue = dieValue;
		this.type = type; 
	}

	@Override
	public void giveImmediateEffect(Player player) {
		// TODO Auto-generated method stub
		
	}

}
