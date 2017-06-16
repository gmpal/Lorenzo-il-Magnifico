package it.polimi.ingsw.GC_24.client.view;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.model.Model;

public class MiniModel implements MyObserver {

	private final Model miniModel = new Model(); 
	
	@Override
	public void update() {		
	}

	@Override
	public <C> void update(MyObservable o, C change) {

		if (change instanceof Model) {
			this.miniModel = (Model) change;
		} else
			System.out.println("MiniModel: error --> I didn't receive a Model");
	}

	public Model getMiniModel() {
		return miniModel;
	}

/*	public void setMiniModel(Model miniModel) {
		this.miniModel = miniModel;
	}*/

}
