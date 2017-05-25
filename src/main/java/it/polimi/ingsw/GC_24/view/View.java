
package it.polimi.ingsw.GC_24.view;


import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.GC_24.model.Model;

public class View extends Observable implements Observer {

	
	
	public View (Model game){
		game.addObserver(this);
	//	System.out.println(game);
	}
	
	//method input
	public void input(int input){
		System.out.println("View here. I'm notifying my observers with an input");
		this.notifyObservers(input);
	}
	

	@Override
	public void update(Observable o, Object arg) {

		System.out.println("View here. I've been notified by the controller with an update");
		/* IN base a ciò che riceve decide cosa stampare su schermo*/
		System.exit(0);

	}

	
	
}
