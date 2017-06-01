package it.polimi.ingsw.GC_24.network.multi;

import java.io.ObjectInputStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;


//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientInHandler extends MyObservable{

	private ObjectInputStream objFromServer;

	public ClientInHandler(ObjectInputStream objFromServer) {
		this.objFromServer = objFromServer;
	}

	public void start() {
		while (true) {
			// reads a new Line from the Scanner
			Object obj = 
			// print the message sent by the server
			System.out.println(line);
		}
	}


}
