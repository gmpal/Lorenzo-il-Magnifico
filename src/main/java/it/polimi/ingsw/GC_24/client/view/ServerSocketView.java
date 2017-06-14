package it.polimi.ingsw.GC_24.client.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;

//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends MyObservable implements Runnable, MyObserver {

	private Socket socket;
	private ObjectOutputStream objToClient;
	private ObjectInputStream objFromClient;
	private boolean end;

	// constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerSocketView(Socket socket) throws IOException {

		this.objToClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		objToClient.flush();
		this.objFromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		this.socket = socket;
		
	}

	/**
	 * This methods keeps receiving Objects from the socket Input Stream and
	 * handling them, giving a response. If the request contains "EXIT" the
	 * connection is closed
	 */
	@Override
	public void run() {

		try {
			

			while (true) {

				Map<String, Object> request = (Map<String, Object>) objFromClient.readObject();
				System.out.println("ServerIn: object as MAP received from client");
				this.notifyMyObservers(request);

				
			}

		} catch (ClassNotFoundException ioe) {
			ioe.printStackTrace();
		} catch (EOFException eof) {
			System.out.println("SERVERIn: end of file reached");
		} catch (IOException io) {
			io.printStackTrace();
			
		}

	}


	
	// IN CHE MODO LA questa VIEW GESTISCE CIO' CHE RICEVE?
	// è davvero lei che gestisce o si limita ad inoltrare al controller?
	// oppure --> in base al tipo di richiesta decide se inoltrare oppure no

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 * @throws IOException 
	 */
	private String handleRequestFromClient(Map<String, Object> request) throws IOException {
		System.out.println("ServerIn: handling the request...");
		Set<String> command = request.keySet();
		if (command.contains("TEST")) {
			SetOfValues setofvalues = (SetOfValues) request.get("TEST");
			notifyMyObservers(setofvalues);
			return "okay";
		}

		if (command.contains("player")) {
			Player player = tokenizeFromPLayer((String) request.get("player"));

		}
		
		if (command.contains("colours")) {
			List<String> playerColoursArray = PlayerColour.getValues();
			HashMap<String, Object> colori = new HashMap<String, Object>();
			colori.put("colours", playerColoursArray);
			objToClient.writeObject(colori);
			objToClient.flush();
			System.out.println("ServerOut: ArrayListOfColours sent");
			return " ArrayListOfColours sent";
		}

		return "bad command";

	}

	/** Creates a new Player from a string containing his name and his colour */
	public Player tokenizeFromPLayer(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string);
		Player player = new Player((String) tokenizer.nextToken(), PlayerColour.valueOf(tokenizer.nextToken()));
		return player;
	}
	
	@Override
	public void update() {
		System.out.println("ServerOut: I have been notified!");

	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		System.out.println("ServerOut: I have been notified by " +observed.getClass().getSimpleName());
		
		try {
			objToClient.writeObject(change);
			objToClient.flush();
			System.out.println("ServerOut: I have sent the change");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}