package it.polimi.ingsw.GC_24.network.multi;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.values.SetOfValues;

//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerIn extends MyObservable implements Runnable {

	private Socket socket;
	private ObjectInputStream objFromClient;
	private boolean end;

	// constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerIn(Socket socket) throws IOException {

		this.socket = socket;
		end = false;
	}

	/**
	 * This methods keeps receiving Objects from the socket Input Stream and
	 * handling them, giving a response. If the request contains "EXIT" the
	 * connection is closed
	 */
	@Override
	public void run() {

		try {
			objFromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			while (!end) {

				Map<String, Object> request = (Map<String, Object>) objFromClient.readObject();
				System.out.println("ServerIn: object as MAP received from client");
				this.handleRequestFromClient(request);

				end = request.containsKey("EXIT");
			}

		} catch (ClassNotFoundException ioe) {
			end = true;
		} catch (EOFException eof) {
			System.out.println("SERVERIn: end of file reached");
		} catch (IOException io) {
			io.printStackTrace();
			end = true;
		}

		System.out.println("SERVERIn: Socket connection closed");

		try {
			objFromClient.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// IN CHE MODO LA questa VIEW GESTISCE CIO' CHE RICEVE?
	// è davvero lei che gestisce o si limita ad inoltrare al controller?
	// oppure --> in base al tipo di richiesta decide se inoltrare oppure no

	/**
	 * This method analyzes the incoming HashMap. If it finds specific keywords
	 * in the keySet, it does different things with different objects
	 */
	private String handleRequestFromClient(Map<String, Object> request) {
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

		return "bad command";

	}

	/** Creates a new Player from a string containing his name and his colour */
	public Player tokenizeFromPLayer(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string);
		Player player = new Player((String) tokenizer.nextToken(), PlayerColour.valueOf(tokenizer.nextToken()));
		return player;
	}

}
