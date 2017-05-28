package it.polimi.ingsw.GC_24.network.multi;

import java.io.PrintWriter;
import java.util.Scanner;

public class ClientOutHandler implements Runnable {
	/**
	 * contains the writer which is used to send messages to the server
	 */
	private PrintWriter socketOut;

	/**
	 * creates a new Handler responsible to read the mosse from command line and
	 * send them to the server
	 *
	 * @param socketOut
	 */
	public ClientOutHandler(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	/**
	 * executes the client handler
	 */
	public void run() {
		// creates a new scanner to read input from standard input
		Scanner stdin = new Scanner(System.in);
		while (true) {
			// read the string from the standard input
			String inputLine = stdin.nextLine();
			// sends the read string to the server
			socketOut.println(inputLine);
			socketOut.flush();
		}
	}
}
