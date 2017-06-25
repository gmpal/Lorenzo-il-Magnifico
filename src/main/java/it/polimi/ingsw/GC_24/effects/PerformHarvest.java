package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.*;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformHarvest extends PerformActivity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757590984515720227L;
	private List<ImmediateEffect> immediateEffectsHarvest;

	// constructor
	public PerformHarvest(String name, int dieValue) {
		super(name, dieValue);
	}

	@Override
	public void giveImmediateEffect(Player player) {
		List<Development> cards = player.getMyBoard().getPersonalTerritories().getCards();
		ImmediateEffect im;
		for (Development card : cards) {
			Territories t = (Territories) card;
			im = t.getEffectForHarvest();
			if (im != null && t.getDieValueHarvest() <= dieValue) {
				immediateEffectsHarvest.add(im);
			}
		}
	}

	public List<ImmediateEffect> getImmediateEffectsHarvest() {
		return immediateEffectsHarvest;
	}

	public void setImmediateEffectsHarvest(List<ImmediateEffect> immediateEffectsHarvest) {
		this.immediateEffectsHarvest = immediateEffectsHarvest;
	}

	@Override
	public void assignParameters(String string) {
		//THE STRING CONTAINS THE NUMBER OF SERVANTS I WANT TO INCREMENT MY HARVEST
		

}
}
