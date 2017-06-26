package it.polimi.ingsw.GC_24.effects;

import java.util.*;
import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.cards.Buildings;
import it.polimi.ingsw.GC_24.model.Player;

public class PerformProduction extends PerformActivity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4975317837362387770L;
	private List<ImmediateEffect> immediateEffectsProduction;

	// constructor
	public PerformProduction(String name, int dieValue) {
		super(name, dieValue);
	}

	@Override
	public void giveImmediateEffect(Player player) {
		List<Development> cards = player.getMyBoard().getPersonalBuildings().getCards();
		ImmediateEffect im;
		ImmediateEffect im1;
		for (Development card : cards) {
			Buildings b = (Buildings) card;
			im = b.getProductionEffect();
			im1 = b.getProductionEffect();
			if (b.getDieValueProduction() <= dieValue) {
				if (im != null) {
					immediateEffectsProduction.add(im);
				}
				if (im1 != null) {
					immediateEffectsProduction.add(im1);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Perform Production: with a starting die value of " + getDieValue();
	}

	//getter and setter
	public List<ImmediateEffect> getImmediateEffectsHarvest() {
		return immediateEffectsProduction;
	}

	public void setImmediateEffectsHarvest(List<ImmediateEffect> immediateEffectsProduction) {
		this.immediateEffectsProduction = immediateEffectsProduction;
	}

	@Override
	public void assignParameters(String string) {
		//THE STRING CONTAINS THE NUMBER OF SERVANTS I WANT TO INCREMENT MY PRODUCTION WITH
		
	}
}
