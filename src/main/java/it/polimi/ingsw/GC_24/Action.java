package it.polimi.ingsw.GC_24;

public abstract class Action {
	

		private final Model gioco;
		
		//constructor
		public Action(Model gioco){
			this.gioco=gioco;
		}
		
		//getters and setters (setter included in constructor)
		protected Model getGioco(){
			return this.gioco;
		}
		
		
		public abstract void run();
		

}
