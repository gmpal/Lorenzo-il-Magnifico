package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;

public abstract class Action {
	

		private final Model game;
		private Player player;
		
		//constructor
		public Action(Model game, Player player){
			this.game=game;
			this.player=player;
		}
		
		//getters and setters (setter included in constructor)
		protected Model getGame(){
			return this.game;
		}
		
		public Player getPlayer(){
			return this.player;
		}
		
		
		public abstract void run(Model game);
		

}
