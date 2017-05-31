package it.polimi.ingsw.GC_24.network.multi;

import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

//ClientOutHandler is observes the ViewPLayer,
//whenever the viewPLayer communicates something, ClientOutHandler is notified by ViewPLayer
//and send the message to the server
public class ClientOutHandler implements Runnable, MyObserver {

	
	private boolean start;
	private PrintWriter socketOut;
	

	public ClientOutHandler(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	
	public void run() {
		// creates a new scanner to read input from standard input
		Scanner stdin = new Scanner(System.in);
		while (true) {
			if(start){ 
				sockeOut.println(change);
				start=false;
			}
		}
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		System.out.println("ClientOutHandler here: I have been notified by the PlayerView");
		socketOut.println(change);
		
	}
}
