package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	
	//MAIN --> Crea un client e fa partire il metodo startClient();
	
	public static void main(String[] args) {
		Client client = new Client();
		
		try {
			client.startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private final static int PORT = 28469;
	private final static String IP="127.0.0.1";
	
	//Crea un socket e un FixedThreadPool, poi lancia un nuovo ClientInHandler e un nuovo ClientOutHandler in parallelo
	public void startClient() throws IOException {
		
		Socket socket = new Socket(IP,PORT);
		System.out.println("Connection established");
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.submit(new ClientInHandler(new Scanner(socket.getInputStream())));
		executor.submit(new ClientOutHandler(new PrintWriter(socket.getOutputStream())));
	}
}
