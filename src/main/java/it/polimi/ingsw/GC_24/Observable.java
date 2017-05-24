package it.polimi.ingsw.GC_24;

import java.util.ArrayList;
import java.util.List;

public abstract class MyObservable {
	
	private List<Observer> observers;
		
		public Observable(){
				observers=new ArrayList<Observer>();
		}
		
		public void addObserver(Observer o){
				observers.add(o);
		}
	
		public void removeObserver(Observer o){
			this.observers.remove(o);
		}
	
		
		public void notifyObservers(){
			for(Observer o: this.observers){
				o.update1();
			}
		}
		
		public <C> void notifyObservers(C c){
			for(Observer o: this.observers){
				o.update(c);
			}
		}

}
