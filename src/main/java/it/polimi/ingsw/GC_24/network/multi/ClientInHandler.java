package it.polimi.ingsw.GC_24.network.multi;

import java.util.Scanner;

public class ClientInHandler implements Runnable {

	private Scanner socketIn;

	public ClientInHandler(Scanner socketIn) {
		this.socketIn = socketIn;
	}

	public void run() {
		while (true) {
			// reads a new Line from the Scanner
			String line = socketIn.nextLine();
			// print the message sent by the server
			System.out.println(line);
		}
	}
}
