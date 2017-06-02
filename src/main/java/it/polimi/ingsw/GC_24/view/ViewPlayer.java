
package it.polimi.ingsw.GC_24.view;


import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;


public abstract class ViewPlayer extends MyObservable implements MyObserver {

		protected String name;
		protected String colour;
		
		public String getName() {
			return name;
		}
		
		public String getColour() {
			return colour;
		}
		
		public abstract void start();
		
		public abstract String setName();
		
		public abstract String setColour();
		
		public abstract String createMessage(String name, String Colour);

}