package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformProduction;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.places.ProductionPlace;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class ProductionAction extends ActivityAction {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private ProductionPlace productionPlace;
	private int finalActionValue = 0;

	public ProductionAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.productionPlace = (ProductionPlace) place;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.payValue(new Servant(this.servants));
		this.getProductionTileValues();
		this.getFinalActionValue();
		this.createProductionEffect();
		return immediateEffects;
	}

	/**
	 * ##PERMANENT EFFECT CHECK HERE: Increase Die Value Production## This method
	 * check if player has a card with Permanent Effect "IncreaseDieValueProduction"
	 * and set the final action value.
	 */
	public void getFinalActionValue() {
		List<PermanentEffect> peList = player.getPermanentEffectList("increaseDieValueProduction");
		for (int i = 0; i < peList.size(); i++) {
			IncreaseDieValueActivity pe = (IncreaseDieValueActivity) peList.get(i);
			this.finalActionValue += pe.getIncreaseDieValue();
		}
		this.finalActionValue += familyMember.getMemberValue() - productionPlace.getAdditionalCostDice() + servants;
		if (this.finalActionValue < 0) {
			finalActionValue=0;
		}
	}

	private void createProductionEffect() {

		immediateEffects.add(new PerformProduction("performProduction", finalActionValue));

	}

	private void getProductionTileValues() {
		player.getMyBoard().getBonusTile().giveProductionValues(player.getMyValues());

	}

	public int getFinalValue() {
		return finalActionValue;
	}
}
