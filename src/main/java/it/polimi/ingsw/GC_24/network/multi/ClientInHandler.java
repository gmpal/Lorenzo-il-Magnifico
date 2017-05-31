package it.polimi.ingsw.GC_24.network.multi;

import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;


//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientInHandler extends MyObservable implements Runnable {

	private Scanner socketIn;

	public ClientInHandler(Scanner socketIn) {
		this.socketIn = socketIn;
	}

	public void run() {
		while (true) {
			// reads a new Line from the Scanner
			String line = socketIn.nextLine();
			// print the message sent by the server
			System.out.println(line);
		}
	}


}
