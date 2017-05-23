package it.polimi.ingsw.GC_24;

import java.util.Observable;
import java.util.Observer;

public class View extends Observable implements Observer {

	private Controller controller;
	
	public View (Controller controller){
		controller.addObserver(this);
		this.controller=controller;
	}
	
	public void input(String input){
		System.out.println("View here. I'm notifying my observers with an input");
		this.notifyObservers(input);
	}
	
	public <C> void update (C change){
		System.out.println("View here. I've been notified by the controller with an update");
		/* IN base a ciò che riceve decide cosa stampare su schermo*/
		System.exit(0);
	}
	
	public void update() {
		System.out.println("View here. I've been notified by the controller");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	


}
