package it.polimi.ingsw.GC_24;

public interface MyObserver {
	public <C> void update(MyObservable o, C change);
}
