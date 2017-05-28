package it.polimi.ingsw.GC_24.network.multi;

import java.util.Scanner;

public class ClientInHandler implements Runnable {
	/**
	 * contains the Scanner which is used to read messages from the server
	 */
	private Scanner socketIn;

	/**
	 * creates a new Handler for the incoming connections
	 * 
	 * @param socketIn
	 *            is the scanner which is used to read messages that are sent
	 *            from the server
	 */

	public ClientInHandler(Scanner socketIn) {
		this.socketIn = socketIn;
	}

	/**
	 * executes the client handler
	 */
	public void run() {
		while (true) {
			// reads a new Line from the Scanner
			String line = socketIn.nextLine();
			// print the message sent by the server
			System.out.println(line);
		}
	}
}
