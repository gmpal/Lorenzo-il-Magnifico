package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.*;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.cards.Buildings;
import it.polimi.ingsw.GC_24.model.cards.Development;
/**
 * this type of effect lets the player perform a production without
 * placing a family member, setting a starting value that can be increased with
 * servants
 */
public class PerformProduction extends PerformActivity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4975317837362387770L;
	private List<ImmediateEffect> immediateEffectsProduction = new ArrayList<>();

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
			im1 = b.getProductionEffect1();

			if (im != null && im instanceof ValueEffect) {
				((ValueEffect) im).giveImmediateEffect(player);
			}

			if (b.getDieValueProduction() <= dieValue) {
				if (im != null && !(im instanceof ValueEffect)) {
					immediateEffectsProduction.add(im);
				}
				if (im1 != null && !(im1 instanceof ValueEffect)) {
					immediateEffectsProduction.add(im1);
				}
			}
		}
		;
	}

	@Override
	public String toString() {
		return "Perform Production: with a starting die value of " + getDieValue();
	}

	public List<ImmediateEffect> getImmediateEffectsProduction() {
		return immediateEffectsProduction;
	}

	public void setImmediateEffectsHarvest(List<ImmediateEffect> immediateEffectsProduction) {
		this.immediateEffectsProduction = immediateEffectsProduction;
	}

	@Override
	public String generateParametersRequest() {
		String response = "You can increment the value of the Production using servants.\n How many servants do you want to use? ";
		return response;
	}

	@Override
	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects) {
		secondaryInteractiveEffects.addAll(getImmediateEffectsProduction());
		return secondaryInteractiveEffects;
	}
}