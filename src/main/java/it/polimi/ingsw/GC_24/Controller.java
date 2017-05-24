package it.polimi.ingsw.GC_24;

import java.util.Observer;
import java.util.Observable;


public class Controller extends MyObservable implements MyObserver {

	
		/*DEVE per forza osservare la VIEW, deve per forza osservare il GAME*/
	
	
	private final Model game;
	
	//constructor
	public Controller(Model game, View view){
		this.game=game;
		game.addObserver(this);
		
		view.addObserver(this);
		this.addObserver(view);
	}
	@Override
	public void update1 (Observable game, Object change){
		System.out.println("Controller here. I've been notified by the model with an action");
		
		/*
		 * Riceve aggiornamenti su modifiche nello stato del model;
		 * Deve notificare la view*/
		this.notifyObservers(change);
	}
	
	public void update2 (Observable view, Object change){
		System.out.println("Controller here. I've been notified by the view with an action");
		Action action;
		
		/*
		 * cosa fare con l'azione, quali sono le possibili azioni
		 * in base a cosa riceve crea l'azione specificata e conclude con
		 * action.esegui();*/
		
	//	action.esegui();
	
	}
}

		
	

	
	

	
}
