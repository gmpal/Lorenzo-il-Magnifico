package it.polimi.ingsw.GC_24.model.board;

import java.util.*;

import it.polimi.ingsw.GC_24.model.places.HarvestPlace;
import it.polimi.ingsw.GC_24.model.places.Place;

public class Harvest extends Activity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173055480132725602L;
	// private List<HarvestPlace> harvestArray = new ArrayList<>();
	private boolean placesLocked;
	private int numPlayers;
	/** Max number of player's familyMember in Production */
	private static final int FACTOR = 2;
	/** Cost of first place */
	private static final int COSTDICE = 1;
	/** Additional Cost dice on the first place */
	private static final int ADDITIONALCOST = 0;
	/** Additional Cost dice from the second place */
	private static final int ADDITIONALCOST2 = 3;

	// constructor
	public Harvest(boolean placesLocked, int numPlayers) {
		this.placesLocked = placesLocked;
		this.numPlayers = numPlayers;
		placesArray = createHarvest();
	}

	/**
	 * @return arrayList of empty HarvestPlaces needed for the Harvest
	 */
	public ArrayList<Place> createHarvest() {
		for (int num = 0; num < numPlayers * FACTOR; num++) {
			if (num == 0) {
				this.placesArray.add(new HarvestPlace(COSTDICE, ADDITIONALCOST));
			} else
				this.placesArray.add(new HarvestPlace(COSTDICE, ADDITIONALCOST2));
			if (placesLocked && num > 1) {
				placesArray.get(num).setAvailable(false);
			}
		}
		return placesArray;
	}
}