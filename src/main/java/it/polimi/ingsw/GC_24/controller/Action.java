package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.Model;

public abstract class Action {
	

		private final Model game;
		
		//constructor
		public Action(Model game){
			this.game=game;
		}
		
		//getters and setters (setter included in constructor)
		protected Model getGame(){
			return this.game;
		}
		
		
		public abstract void run();
		

}
