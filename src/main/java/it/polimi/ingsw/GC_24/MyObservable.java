package it.polimi.ingsw.GC_24;

import java.util.ArrayList;

import java.util.List;

public abstract class MyObservable {

	private List<MyObserver> MyObservers;
	
	public MyObservable() {
		MyObservers = new ArrayList<MyObserver>();
		//TODO: quando istanzio una sottoclasse come gestisco questo costruttore?
	}

	public void registerMyObserver(MyObserver o) {
		MyObservers.add(o);
	}

	public void unregisterMyObserver(MyObserver o) {
		this.MyObservers.remove(o);
	}
	
	public void unregisterAllMyObserver() {
		this.MyObservers.clear();
	}

	public <C> void notifyMyObservers(C change) {
		for (MyObserver o : this.MyObservers) {
			o.update(this, change);
		}
	}
	public <C> void notifySingleObserver(MyObserver o, C change) {
		
			o.update(this, change);
		
	}
}
