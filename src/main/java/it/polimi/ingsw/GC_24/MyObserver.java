package it.polimi.ingsw.GC_24;

public interface MyObserver {
	public boolean update();
	public <O extends MyObservable,C> boolean update(O observed, C change);
}
