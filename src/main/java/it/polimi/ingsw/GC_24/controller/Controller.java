package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.model.Model;

//SOLO UN CONTROLLER LATO SERVER per ogni partita
public class Controller implements MyObserver {

	private final Model game;
	
	//constructor

	public Controller(Model game) {

		this.game = game;
		
	}
	
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		
		System.out.println("Controller here: I have been notified by" +observed.getClass().getSimpleName());
		
		System.out.println("Controller: i received this:"+change);
		
		
	}


	

	
}

		
