package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.view.ViewPlayer;

//ClientOutHandler observes the ViewPLayer,
//whenever the viewPLayer communicates something, ClientOutHandler is notified by ViewPLayer
//and send the message to the server
public class ClientOut implements MyObserver {

	
	private ObjectOutputStream objToServer;
	private ViewPlayer view;
	

	public ClientOut(ObjectOutputStream objToServer, ViewPlayer view) throws IOException {
		this.objToServer = objToServer;
		this.view=view;
		this.objToServer.flush();
		view.registerMyObserver(this);
	}

	
	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		System.out.println("ClientOutHandler here: I have been notified by "+observed.getClass().getSimpleName());
		try {
			objToServer.writeObject(change);
			objToServer.flush();
			System.out.println("ClientOutHandler here: I have sent the change to the Server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update() {
		System.out.println("ClientOutHandler here: I have been notified");
		
	}


}
