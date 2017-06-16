package it.polimi.ingsw.GC_24.client.view;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.State;

//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientSocketViewCLI extends MyObservable implements ClientSocketViewInterface {

	private ObjectInputStream objFromServer;
	private ObjectOutputStream objToServer;
	private ViewCLI view;
	private MiniModel miniModel;

	private boolean end = false;
	private boolean colourReceived;

	public ClientSocketViewCLI(ObjectInputStream objFromServer, ObjectOutputStream objToServer, ViewCLI view, MiniModel miniModel) {
		this.objToServer = objToServer;
		this.objFromServer = objFromServer;
		this.view = view;
		this.miniModel = miniModel;
		this.registerMyObserver(view);
		this.registerMyObserver(miniModel);
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

		if (command.contains("colours")) {
			List<String> playerColoursArray = (List<String>) request.get("colours");

			notifyMyObservers(playerColoursArray);
		}

		if (command.contains("coloursAnswer")) {
			String colourAnswer = (String) request.get("coloursAnswer");
			notifyMyObservers(colourAnswer);
		}
		
		if (command.contains("gameStarted")) {
			String gameStarted = (String) request.get("gameStarted");
			notifyMyObservers(gameStarted);
		}
		if (command.contains("Model")) {
			Model modelReceived = (Model) request.get("Model");
			notifySingleObserver(miniModel, modelReceived);
		}
	}

}
