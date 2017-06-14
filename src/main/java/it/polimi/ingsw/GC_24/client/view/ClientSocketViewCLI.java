package it.polimi.ingsw.GC_24.client.view;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.values.SetOfValues;

//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientSocketViewCLI extends MyObservable implements ClientSocketViewInterface {

	private ObjectInputStream objFromServer;
	private ObjectOutputStream objToServer;
	private ViewCLI view;
	
	private boolean end = false;

	public ClientSocketViewCLI(ObjectInputStream objFromServer,ObjectOutputStream objToServer, ViewCLI view) {
		this.objToServer = objToServer;
		this.objFromServer = objFromServer;
		this.view = view;
		this.registerMyObserver(view);
		view.registerMyObserver(this);
	}

	@Override
	public void run() {
		try {
			while (!end) {

				Map<String, Object> requestFromServer;

				requestFromServer = (Map<String, Object>) objFromServer.readObject();
				this.handleRequestFromServer(requestFromServer);
			}
		} catch (EOFException e) {
			System.out.println("End Of File reached");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	/**
	 * Based on the key of the object received, this method handles the request
	 */
	@Override
	public void handleRequestFromServer(Map<String, Object> request) {
		Set<String> command = request.keySet();

		if (command.contains("TEST")) {
			SetOfValues set = (SetOfValues) request.get("TEST");
			System.out.println("ClientIn: preso oggetto test");
			System.out.println("ClientIn: ecco cosa ho ricevuto!");
			System.out.println(set.toString());

			if (command.contains("colori")) {
				List<String> playerColoursArray = (ArrayList<String>) request.get("colori");
				System.out.println("ClientIn: preso playerColoursArray");
				System.out.println("ClientIn: ecco cosa ho ricevuto!");
				System.out.println(playerColoursArray.toString());

				notifyMyObservers(playerColoursArray);
			}
		}

	}

}
