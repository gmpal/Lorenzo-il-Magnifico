package it.polimi.ingsw.GC_24.client.view;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.polimi.ingsw.GC_24.MyObservable;

import it.polimi.ingsw.GC_24.values.MilitaryPoint;
import it.polimi.ingsw.GC_24.values.SetOfValues;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.State;


//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientSocketViewCLI extends MyObservable implements ClientSocketViewInterface {

	private ObjectInputStream objFromServer;
	private ObjectOutputStream objToServer;
	private ViewCLI view;
	private boolean end = false;
	private boolean colourReceived;

	public ClientSocketViewCLI(ObjectInputStream objFromServer, ObjectOutputStream objToServer, ViewCLI view) {
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
			e.printStackTrace();
		}

	}

	@Override
	public <C> void update(MyObservable o, C change) {
		try {
			objToServer.writeObject(change);
			objToServer.flush();

		} catch (IOException e) {
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


		/*Contains the array of colours updated at the moment when requested*/

		if (command.contains("colours")) {
			List<String> playerColoursArray = (List<String>) request.get("colours");
			notifyMyObservers(playerColoursArray);
		}

		/*Contains the answer if the colour has already been chosen or not*/

		if (command.contains("coloursAnswer")) {
			String colourAnswer = (String) request.get("coloursAnswer");
			if (colourAnswer.equals("Colour Available")) {
				view.setColourAvailable(1);
			} else if (colourAnswer.equals("Colour Not Available")) {
				view.setColourAvailable(0);
			}
		}
		
		/*IN THIS CASE the request is handled by the viewCLI*/
		if (command.contains("cost1")) {
			SetOfValues cost1 = (SetOfValues) request.get("Cost1");
			SetOfValues cost2 = (SetOfValues) request.get("Cost2");
			MilitaryPoint militaryPoints = (MilitaryPoint) request.get("Requirements");
			view.chooseAlternativeCost(cost1, cost2, militaryPoints);
		}
		if (command.contains("Model")) {
			Model modelReceived = (Model) request.get("Model");
			notifyMyObservers(modelReceived);
		}
	}

}
