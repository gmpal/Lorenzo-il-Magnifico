package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Action {
	

		//private final Model game;
		private Player player;
		private FamilyMember familyMember;
		private Place place;*/
		
		//constructor
		public Action(Player player, FamilyMember familyMember, Place place) {
			this.player=player;
			this.familyMember = familyMember;
			this.place = place;
		}
		
		//getters and setters (setter included in constructor)		
		public Player getPlayer(){
			return this.player;
		}
		
		
		public abstract void run(Player player, FamilyMember familyMember, Place place);
		

}
