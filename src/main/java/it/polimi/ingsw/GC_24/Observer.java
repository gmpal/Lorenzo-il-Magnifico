package it.polimi.ingsw.GC_24;

public interface MyObserver {
	
		public void update1();
		public void update2();
		
		public void update1(Observable observable, Object change);
		public void update2(Observable observable, Object change);
		
}
