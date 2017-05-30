package it.polimi.ingsw.GC_24.network.multi;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientOutHandler implements Runnable {

	private PrintWriter socketOut;

	public ClientOutHandler(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	
	public void run() {
		// creates a new scanner to read input from standard input
		Scanner stdin = new Scanner(System.in);
		while (true) {
			// read the string from the standard input
			String inputLine = stdin.nextLine();
			// sends the read string to the server
			socketOut.println(inputLine);

		}
	}
}
