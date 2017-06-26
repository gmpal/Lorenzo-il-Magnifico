package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformHarvest extends PerformActivity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757590984515720227L;
	private List<ImmediateEffect> immediateEffectsHarvest  = new ArrayList<>();;
	

	// constructor
	/*DIE VALUE ALREADY CONTAINS THE PERMANENT INCREMENT*/
	public PerformHarvest(String name, int dieValue) {
		super(name, dieValue);
	}

	//STEP ONE
	@Override
	public void giveImmediateEffect(Player player) {
		List<Development> cards = player.getMyBoard().getPersonalTerritories().getCards();
		ImmediateEffect im;
		for (Development card : cards) {
			Territories t = (Territories) card;
			im = t.getEffectForHarvest();
			if (im != null && im instanceof ValueEffect ){
				((ValueEffect)im).giveImmediateEffect(player);
			}
			else if (im != null && t.getDieValueHarvest() <= dieValue+incrementServants) {
				immediateEffectsHarvest.add(im);
				
			}
		}
	}

	
	//STEP TWO
	public List<ImmediateEffect> getImmediateEffectsHarvest() {
		return immediateEffectsHarvest;
	}

	public void setImmediateEffectsHarvest(List<ImmediateEffect> immediateEffectsHarvest) {
		this.immediateEffectsHarvest = immediateEffectsHarvest;
	}

	

}
