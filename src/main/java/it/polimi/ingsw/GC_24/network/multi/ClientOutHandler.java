package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

//ClientOutHandler is observes the ViewPLayer,
//whenever the viewPLayer communicates something, ClientOutHandler is notified by ViewPLayer
//and send the message to the server
public class ClientOutHandler implements MyObserver {

	
	private ObjectOutputStream objToServer;
	

	public ClientOutHandler(ObjectOutputStream objToServer) {
		this.objToServer = objToServer;
	}

	
	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		System.out.println("ClientOutHandler here: I have been notified by "+observed.getClass().getSimpleName());
		try {
			objToServer.writeObject(change);
			System.out.println("ClientOutHandler here: I have sent the change to the Server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update() {
		System.out.println("ClientOutHandler here: I have been notified by the PlayerView");
		
	}
}
