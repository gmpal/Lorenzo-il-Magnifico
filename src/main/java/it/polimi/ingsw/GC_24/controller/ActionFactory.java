package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.values.SetOfValues;

/**
 * This is a factory thats only job is creating actions By encapsulating actions
 * creation, we only have one place to make modifications.
 *
 */
public class ActionFactory {

	// This could be used as a static method if we
	// are willing to give up subclassing it

	public Action makeAction(Model game, String familiar, String zone, String floor, String servants,
			SetOfValues cost, SetOfValues setOfSale) {

		Action newAction = null;

		if (zone.equalsIgnoreCase("Territories") || zone.equalsIgnoreCase("Characters")
				|| zone.equalsIgnoreCase("Buildings") || zone.equalsIgnoreCase("Ventures")) {

			newAction = new ActionTower(game, familiar, zone, floor, servants, cost, setOfSale);

		}

		if (zone.equalsIgnoreCase("Harvest")) {

			newAction = new HarvestAction(game, familiar, zone, floor, servants);

		}
		
		if (zone.equalsIgnoreCase("Production")) {

			newAction = new ProductionAction(game, familiar, zone, floor, servants);

		}


		if (zone.equalsIgnoreCase("Council")) {

			newAction = new CouncilPalaceAction(game, familiar, zone, floor, servants);

		}
		if (zone.equalsIgnoreCase("Market")) {

			newAction = new MarketAction(game, familiar, zone, floor, servants);

		}

		return newAction;

	}
}
