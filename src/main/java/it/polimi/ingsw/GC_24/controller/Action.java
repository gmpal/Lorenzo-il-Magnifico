package it.polimi.ingsw.GC_24.controller;

import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
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
		public abstract List<ImmediateEffect> run();
		
		
		
		//getters and setters
		public Place getPlace() {
			return place;
		}

		public void setPlace(Place place) {
			this.place = place;
		}

		public FamilyMember getFamilyMember() {
			return familyMember;
		}

		public void setFamilyMember(FamilyMember familyMember) {
			this.familyMember = familyMember;
		}

		public void setPlayer(Player player) {
			this.player = player;
		}

	

	
		
		

}
