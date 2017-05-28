package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	
	//Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) {
		Server server = new Server();
		
		try {
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static final int PORT = 28469;
	//private static final String IP = "127.0.0.1";
	
	
	//Crea un Cached Threa Pool e un serverSocket , poi si mette in attesa dei socket
	//Quando un client si connette crea un ClientHandler e lo fa partire
	public void startServer() throws IOException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server ready");
		
		while(true){
			try {
				Socket socket = serverSocket.accept();
				executor.submit(new ClientHandler(socket));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
		
		executor.shutdown();
		serverSocket.close();
	}
	
}
