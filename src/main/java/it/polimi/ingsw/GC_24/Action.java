package it.polimi.ingsw.GC_24;

public abstract class Action {
	
	private final Model game;

	//constructor
	public Action(Model game) {
		this.game = game;
	}

	protected Model getGame() {
		return game;
	}
	
	public abstract void run();
}
