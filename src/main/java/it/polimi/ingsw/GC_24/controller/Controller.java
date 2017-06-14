package it.polimi.ingsw.GC_24.controller;

import java.util.HashMap;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.values.SetOfValues;

//Just one server's side controller for each game
public class Controller extends MyObservable implements MyObserver {

	private final Model game;
	
	//constructor

	public Controller(Model game) {

		this.game = game;
		
	}

	@Override
	public void update() {		
	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		
		System.out.println("Controller: I have been notified by " +observed.getClass().getSimpleName());
		
		System.out.println("Controller: i received this :"+change);
		System.out.println("Controller: i know  it is a " +change.getClass().getSimpleName());
		System.out.println("So I cast it");
		SetOfValues set = (SetOfValues) change;
		System.out.println("Casted! Now I change something");
		set.getCoins().addQuantity(5);
		System.out.println("Added 5 coins");
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("TEST", set);
		System.out.println("Created a HashMap with TEST key assigned to the set");
		System.out.println("Only this time i notify my temporary observer ServerOut with the set");
		this.notifyMyObservers(obj);
		
	}

}

		
