package it.polimi.ingsw.GC_24.controller;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;
import it.polimi.ingsw.GC_24.places.TowerPlace;

public class ActionTower extends Action {
	private List<ImmediateEffect> immediateEffects=new ArrayList<>();

	public ActionTower(Player player, FamilyMember familyMember, Place place) {
		super(player, familyMember, place);
	}

	@Override
	public List<ImmediateEffect> run(Player player, FamilyMember familyMember, Place place) {
		place.setFamMemberOnPlace(familyMember);
		familyMember.setAvailable(false);
		if (place.getValue() != null) {
			place.getValue().addValueToSet(player.getMyValues());
		}
		TowerPlace towerPlace = (TowerPlace) place;
		immediateEffects.add(towerPlace.getCorrespondingCard().getImmediateEffect());
		immediateEffects.add(towerPlace.getCorrespondingCard().getImmediateEffect1());
		giveEffect(immediateEffects);
		return immediateEffects;
	}

	public void giveEffect(List<ImmediateEffect> immediateEffects) {
		String nameEffect;
		for(ImmediateEffect im:immediateEffects){
			nameEffect=im.getName();
			if(nameEffect.equals("value")){
				im.giveImmediateEffect(getPlayer());
				immediateEffects.remove(im);
			}
		}
	}
}
