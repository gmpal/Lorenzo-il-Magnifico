package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.PerformProduction;
import it.polimi.ingsw.GC_24.model.effects.PermanentEffect;
import it.polimi.ingsw.GC_24.model.places.ProductionPlace;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class ProductionAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private ProductionPlace productionPlace;
	private int finalActionValue = 0;

	public ProductionAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.productionPlace = (ProductionPlace) place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";

		answerToPlayer = verifyIfEnoughServants(answerToPlayer);
		answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
		answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
		answerToPlayer = verifyPlaceAvailability(answerToPlayer);
		answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);

		if (answerToPlayer.equals("Answer: \n"))
			return "ok";
		else
			return answerToPlayer;
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
