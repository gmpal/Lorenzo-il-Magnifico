package it.polimi.ingsw.GC_24.board;

import java.util.*;
import it.polimi.ingsw.GC_24.places.HarvestPlace;
import it.polimi.ingsw.GC_24.places.Place;

public class Harvest extends Area {

	
/**
	 * 
	 */
	private static final long serialVersionUID = -7173055480132725602L;
	//	private List<HarvestPlace> harvestArray = new ArrayList<>();
	private boolean placesLocked;
	private int numPlayers;
	/**Max number of player's familyMember in Production*/
	private static final int FACTOR=2; 			
	/**Number of ProductionPlaces with 2 players*/
	private static final int MINPLACES=1;	
	/**Cost of first place*/
	private static final int COSTDICE=1;	
	/**Additional Cost dice on the first place*/
	private static final int ADDITIONALCOST=0;		
	/**Additional Cost dice from the second place*/
	private static final int ADDITIONALCOST2=3;				

	//constructor
	public Harvest(boolean placesLocked, int numPlayers) {
		this.placesLocked=placesLocked;
		this.numPlayers=numPlayers;
		placesArray = createHarvest();
	}
	
	/**inserts empty ProductionPlaces in Production*/
	public ArrayList<Place> createHarvest(){	
		for(int num=0;num<numPlayers*FACTOR;num++){
			if(num==0){
				this.placesArray.add(new HarvestPlace(COSTDICE, ADDITIONALCOST));
			}
			else this.placesArray.add(new HarvestPlace(COSTDICE, ADDITIONALCOST2));
			if (placesLocked && num>1){
				placesArray.get(num).setAvailable(false);
			}
		}
		return placesArray;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (Place p : placesArray){
			if (!p.isAvailable()) {
				builder.append("[Place occupied by the " + p.getFamMemberOnPlace().getPlayerColour() + " player]");
			} else {
				if(p.getValue()!=null)
					builder.append("[Place Available] --> You will have to pay an extra die's cost of: " + p.getValue());
				else
					builder.append("[Place Available]");
				return builder.toString();
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
