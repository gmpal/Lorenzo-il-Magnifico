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

		if (zone.equalsIgnoreCase("territories") || zone.equalsIgnoreCase("characters")
				|| zone.equalsIgnoreCase("buildings") || zone.equalsIgnoreCase("ventures") ||
				zone.equalsIgnoreCase("territory") || zone.equalsIgnoreCase("character")
				|| zone.equalsIgnoreCase("building") || zone.equalsIgnoreCase("venture")) {
			System.out.println("Fabbrica --> Creando un ActionTower");
			newAction = new ActionTower(game, familiar, zone, floor, servants, cost, setOfSale);
		}

		else if (zone.equalsIgnoreCase("Harvest")) {
			System.out.println("Fabbrica --> Creando un harvestAction ");
			newAction = new HarvestAction(game, familiar, zone, floor, servants);
			System.out.println("Fabbrica --> creata un harvestAction ");
		}
		
		else if (zone.equalsIgnoreCase("Production")) {
			System.out.println("Fabbrica --> creando una production action");
			newAction = new ProductionAction(game, familiar, zone, floor, servants);
			System.out.println("Fabbrica --> creata una production action");

		}


		else if (zone.equalsIgnoreCase("Council")) {
			System.out.println("Fabbrica --> creando una councilpalace action ");
			newAction = new CouncilPalaceAction(game, familiar, zone, floor, servants);
			System.out.println("Fabbrica --> creata una councilpalace action ");
		}
		else if (zone.equalsIgnoreCase("Market")) {
			System.out.println("Fabbrica --> creando una market action ");
			newAction = new MarketAction(game, familiar, zone, floor, servants);
			System.out.println("Fabbrica --> creata una market action ");
		}
		return newAction;

	}
}
