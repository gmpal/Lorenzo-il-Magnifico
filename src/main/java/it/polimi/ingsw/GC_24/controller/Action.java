package it.polimi.ingsw.GC_24.controller;

import java.util.List;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.FamilyMember;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.places.Place;

public abstract class Action {
	
		protected Player player;
		protected Place place;
		protected FamilyMember familyMember;
		
		public Action(Player player, FamilyMember familyMember, Place place) {
			this.player = player;
			this.familyMember=familyMember;
			this.place=place;
		}

		//getters and setters (setter included in constructor)		
		public Player getPlayer(){
			return this.player;
		}
		
		
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

		public abstract List<ImmediateEffect> run();
		

}
