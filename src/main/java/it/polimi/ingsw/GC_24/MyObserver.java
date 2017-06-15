package it.polimi.ingsw.GC_24;

public interface MyObserver {
	public void update();
	public <C> void update(MyObservable o, C change);
}
