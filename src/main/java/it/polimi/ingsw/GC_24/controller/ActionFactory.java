package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * This is a factory that creates actions, based on the zone chosed by the player.
 *
 */
public class ActionFactory {


	public Action makeAction(Model game, String familiar, String zone, String floor, String servants,
			SetOfValues cost, SetOfValues setOfSale) {

		Action newAction = null;

		if (zone.equalsIgnoreCase("territories") || zone.equalsIgnoreCase("characters")
				|| zone.equalsIgnoreCase("buildings") || zone.equalsIgnoreCase("ventures") ||
				zone.equalsIgnoreCase("territory") || zone.equalsIgnoreCase("character")
				|| zone.equalsIgnoreCase("building") || zone.equalsIgnoreCase("venture")) {
		
			newAction = new ActionTower(game, familiar, zone, floor, servants, cost, setOfSale);
		}
		else if (zone.equalsIgnoreCase("Harvest")) {
			newAction = new HarvestAction(game, familiar, zone, floor, servants);
		}
		else if (zone.equalsIgnoreCase("Production")) {
			newAction = new ProductionAction(game, familiar, zone, floor, servants);
		}
		else if (zone.equalsIgnoreCase("Council")) {
			newAction = new CouncilPalaceAction(game, familiar, zone, floor, servants);

		}
		else if (zone.equalsIgnoreCase("Market")) {
			newAction = new MarketAction(game, familiar, zone, floor, servants);
		}
		return newAction;

	}
}
