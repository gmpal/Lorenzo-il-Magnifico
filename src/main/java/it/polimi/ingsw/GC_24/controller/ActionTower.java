package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public class ActionTower extends Action {

	public ActionTower(Model partita, String familiar, String zone, String floor, String servants) {
		super(partita, familiar, zone, floor, servants);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verify(Model game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run(Model game) {
		// TODO Auto-generated method stub

	}

}
