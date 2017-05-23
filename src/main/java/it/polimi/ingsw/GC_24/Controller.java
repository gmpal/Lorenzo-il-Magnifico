package it.polimi.ingsw.GC_24;

import java.util.Observer;
import java.util.Observable;


public class Controller extends Observable implements Observer {

	private final Model game;
	
	//constructor
	public Controller(Model game, View view){
		this.game=game;
		view.addObserver(this);
		game.addObserver(this);
	}
	
	public <C> void update (Model game, C change){
		System.out.println("Controlller here. I've been notified by the model with an action");
		
		/*
		 * Riceve aggiornamenti su modifiche nello stato del model;
		 * Deve notificare la view*/
		this.notifyObservers(change);
	}
	
	public <C> void update (View view, C change){
		System.out.println("Controller here. I've been notified by the view with an action");
		Action action;
		/*
		 * cosa fare con l'azione, quali sono le possibili azioni
		 * in base a cosa riceve crea l'azione specificata e conclude con
		 * action.esegui();*/

	//	action.esegui();
	
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	

	
}
