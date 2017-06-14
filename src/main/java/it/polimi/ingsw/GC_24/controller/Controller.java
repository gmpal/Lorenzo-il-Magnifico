package it.polimi.ingsw.GC_24.controller;

import java.util.Observer;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.view.View;

import java.util.Observable;


public class Controller implements Observer {

	private final Model game;
	
	//constructor
	public Controller(Model game, View view) {
		this.game = game;
		view.addObserver(this);
	}
	
	
	@Override
	public void update (Observable game, Object change){
		System.out.println("Controller here. I've been notified by the view with an action");
		/*Do something to the model by an action*/
		//action.run();		
	}
	

	
}

		
