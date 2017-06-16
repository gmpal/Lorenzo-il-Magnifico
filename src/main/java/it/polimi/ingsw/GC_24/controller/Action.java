package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Action {
		
		private FamilyMember familyMember;
		private Place place;
		private int servants;
		
		//constructor
		public Action(Model partita, String familiar, String zone, String floor, String servants) {
			this.familyMember = partita.getCurrentPlayer().getMyFamily().getMemberfromString(familiar);
			this.place = partita.getBoard().getZoneFromString(zone).getPlaceFromString(floor);
			this.servants = Integer.parseInt(servants);
		}
		
				

		
		/**The verify() methods checks if the current action is logically correct, 
		 * and if it returns true, the run method is called */
		public abstract boolean verify(Model game);
		
		/**The run() method executes the action*/
		public abstract void run(Model game);
		

}
