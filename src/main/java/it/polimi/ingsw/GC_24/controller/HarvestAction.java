package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.cards.Characters;
import it.polimi.ingsw.GC_24.model.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.PerformHarvest;
import it.polimi.ingsw.GC_24.model.places.HarvestPlace;
import it.polimi.ingsw.GC_24.model.values.Servant;

public class HarvestAction extends Action {
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private HarvestPlace harvestPlace;
	private int finalActionValue=0;

	public HarvestAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.harvestPlace = (HarvestPlace) place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";
		
			answerToPlayer = verifyIfEnoughServants(answerToPlayer);
			answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
			answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
			answerToPlayer = verifyPlaceAvailability(answerToPlayer);
			answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);
		
		if (answerToPlayer.equals("Answer: \n")) return "ok";
		else return answerToPlayer;
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

	/** ##PERMANENT EFFECT CHECK HERE: Increase Die Value Harvest##
	 * This method check if player has a card with Permanent Effect
	 * "IncreaseDieValueHarvest" and set the final action value
	 */
	public void getFinalActionValue() {
		
		for(int i=0;i<player.getMyBoard().getPersonalCharacters().getCards().size();i++){
			Characters c=(Characters)player.getMyBoard().getPersonalCharacters().getCards().get(i);
			if(c != null && c.getPermanentEffects()!= null && c.getPermanentEffects().getName().equals("increaseDieValueHarvest")){
				IncreaseDieValueActivity pe=(IncreaseDieValueActivity)c.getPermanentEffects();
				this.finalActionValue+=pe.getIncreaseDieValue();
			}
		}
		this.finalActionValue += familyMember.getMemberValue() - harvestPlace.getAdditionalCostDice() + servants;
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
