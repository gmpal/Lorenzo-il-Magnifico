package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Action {
		
		protected FamilyMember familyMember;
		protected Player player;
		protected Place place;
		protected int servants;
		
		//constructor
	public Action(Model game, String familiar, String zone, String floor, String servants) {
			this.player = game.getCurrentPlayer();
			this.familyMember = player.getMyFamily().getMemberfromString(familiar);
			this.place = game.getBoard().getZoneFromString(zone).getPlaceFromString(floor);
			this.servants = Integer.parseInt(servants);
		}
		
				

		
		/**The verify() methods checks if the current action is logically correct, 
		 * and if it returns true, the run method is called */
		public abstract boolean verify();
		
		/**The run() method executes the action*/
		public abstract void run();
		

}
