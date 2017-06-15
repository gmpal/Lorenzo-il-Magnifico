package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class ActionTower extends Action {
	private ImmediateEffect immediateEffects;
	private ImmediateEffect immediateEffects1;

	public ActionTower(Player player, FamilyMember familyMember, Place place) {
		super(player, familyMember, place);
	}

	@Override
	public void run(Player player, FamilyMember familyMember, Place place) {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
		if (place.getValue() != null) {
			place.getValue().addValueToSet(player.getMyValues());
		}
		TowerPlace towerPlace = (TowerPlace) place;
		immediateEffects = towerPlace.getCorrespondingCard().getImmediateEffect();
		immediateEffects1 = towerPlace.getCorrespondingCard().getImmediateEffect1();
		giveEffect(immediateEffects);
		giveEffect(immediateEffects1);
	}

	private void giveEffect(ImmediateEffect immediateEffects) {
		String nameEffect=immediateEffects.getName();
		if(nameEffect.equals("value")){
			immediateEffects.giveImmediateEffect(getPlayer());
		}
		else if(nameEffect.equals("chooseNewCard")){
			
		}
	}
}
