package it.polimi.ingsw.GC_24;

public interface MyObserver {
	public void update();
	public <O extends MyObservable,C> void update(O observed, C change);
}
