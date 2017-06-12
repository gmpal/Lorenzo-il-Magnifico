package it.polimi.ingsw.GC_24.network.multi;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.view.ViewPlayer;

//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientIn extends MyObservable implements Runnable {

	private ObjectInputStream objFromServer;
	private ViewPlayer view;
	private boolean end = false;

	public ClientIn(ObjectInputStream objFromServer, ViewPlayer view) {
		this.objFromServer = objFromServer;
		this.view = view;
		this.registerMyObserver(view);
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

	/**
	 * Based on the key of the object received, this method handles the request
	 */
	private void handleRequestFromServer(Map<String, Object> request) {
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
