package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformHarvest;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.places.HarvestPlace;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class HarvestAction extends ActivityAction {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private HarvestPlace harvestPlace;
	private int finalActionValue = 0;

	public HarvestAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.harvestPlace = (HarvestPlace) place;
	}

	@Override
	public List<ImmediateEffect> run() {
		this.placeFamiliar();
		this.payValue(new Servant(this.servants));
		this.getHarvestTileValues();
		this.getFinalActionValue();
		this.createHarvestEffect();
		return immediateEffects;
	}

	/**
	 * ##PERMANENT EFFECT CHECK HERE: Increase Die Value Harvest## This method check
	 * if player has a card with Permanent Effect "IncreaseDieValueHarvest" and set
	 * the final action value
	 */
	public void getFinalActionValue() {
		List<PermanentEffect> peList = player.getPermanentEffectList("increaseDieValueHarvest");
		for (int i = 0; i < peList.size(); i++) {
			IncreaseDieValueActivity pe = (IncreaseDieValueActivity) peList.get(i);
			this.finalActionValue += pe.getIncreaseDieValue();
		}
		this.finalActionValue += familyMember.getMemberValue() - harvestPlace.getAdditionalCostDice() + servants;
		if (this.finalActionValue < 0) {
			finalActionValue=0;
		}
	}

	private void createHarvestEffect() {
		immediateEffects.add(new PerformHarvest("performHarvest", finalActionValue));
	}

	private void getHarvestTileValues() {
		player.getMyBoard().getBonusTile().giveHarvestValues(player.getMyValues());
	}

	public int getFinalValue() {
		return finalActionValue;
	}

}
