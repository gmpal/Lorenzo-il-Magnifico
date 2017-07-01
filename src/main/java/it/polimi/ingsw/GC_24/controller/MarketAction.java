package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.places.MarketPlace;
import it.polimi.ingsw.GC_24.values.Servant;

public class MarketAction extends Action {
	
	private List<ImmediateEffect> immediateEffects = new ArrayList<>();
	private MarketPlace marketPlace;
	
	

	public MarketAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
		this.marketPlace = (MarketPlace) place;
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";
		
			System.out.println("## --> Dentro il while ");
			answerToPlayer = verifyIfEnoughServants(answerToPlayer);
			System.out.println("## --> Abbastanza servi");
			answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
			System.out.println("## --> verifyIfEnoughServantsForThisPlace");
			answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
			System.out.println("## --> verifyFamilyMemberAvailability ");
			answerToPlayer = verifyPlaceAvailability(answerToPlayer);
			System.out.println("## --> verifyPlaceAvailability ");
		
		if (answerToPlayer.equals("Answer: \n")) return "ok";
		else return answerToPlayer;
	}

	@Override
	public List<ImmediateEffect> run() {

		this.placeFamiliar();
		this.payValue(new Servant(this.servants));
		this.takeValueFromPlace();

		this.takePrivilegeEffectFromMarketPlace();

		return immediateEffects;
	}

	public void takePrivilegeEffectFromMarketPlace() {
		ImmediateEffect im = marketPlace.getPrivilegeEffect();

		if (im != null) {
			immediateEffects.add(im);
		}
	}

	public List<ImmediateEffect> getImmediateEffects() {
		return immediateEffects;
	}
}
