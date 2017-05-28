package it.polimi.ingsw.GC_24.controller;

import java.util.Observer;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.view.View;

import java.util.Observable;

//SOLO UN CONTROLLER LATO SERVER per ogni partita
public class Controller implements Observer {

	private final Model game;
	
	//constructor
	public Controller(Model game, View view) {
		this.game = game;
		view.addObserver(this);
	}
	
	
	@Override
	public void update (Observable o, Object change){
		Action action = (Action) change;
		System.out.println("SERVER CONTROLLER: the thread that is performing the action has been launched");
		/*Do something to the model by an action*/
		action.run(game);		
	}
	

	
}

		
