package it.polimi.ingsw.GC_24.client.view;

public class Buffer {

		private String string;
		private boolean empty;
		
		public synchronized void put(String string){
			while(!empty){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			empty = false;
			this.string = string;
			notifyAll();
		}
		
		public synchronized String get(){
			//Wait until message is available
			while(empty){
				try {
					wait();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			empty = true;
			notifyAll();
			return string;
		}
		
		
}
