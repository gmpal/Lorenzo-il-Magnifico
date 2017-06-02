package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.State;

public class Server {

	private static final int PORT = 28469;
	private List<Player> players;
	private Model game;
	private Controller controller;
	
	// Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) throws RemoteException {
		Server server = new Server();
		

		try {
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// constructor
	public Server() throws RemoteException {
	
	}

	// Crea un Cached Thread Pool e un serverSocket , poi si mette in attesa dei
	// socket
	// Quando un client si connette crea un ClientHandler e lo fa partire
	public void startServer() throws IOException {
		
		ExecutorService threadPool = Executors.newCachedThreadPool();
		System.out.println("SERVER: ThreadPool Created");
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");	
		game = new Model();
		System.out.println("SERVER: Model Created");
		controller = new Controller(game);
		System.out.println("SERVER: Controller Created");
		System.out.println("SERVER: ready");
		while(true){
			try {
				Socket socket = serverSocket.accept();
				System.out.println("SERVER: Connection established");
				ServerOut serverOut = new ServerOut(socket);
				System.out.println("SERVER: ServerOut created");
				ServerIn serverIn = new ServerIn(socket);
				threadPool.submit(serverIn);
				System.out.println("SERVER: ServerIn created");
				
				game.registerMyObserver(serverOut);
				serverIn.registerMyObserver(controller);
		//		this.addClient(serverOut); //now only adds the observer
				System.out.println("SERVER: observers setted!");
				
			
			} catch (IOException e) {
				break;
			}
		}
		
		threadPool.shutdown();
		serverSocket.close();
	}

	//TODO:gestire questo metodo! 
	public void addClient(ServerOut serverOut) {
		game.registerMyObserver(serverOut);
	/*	// Submits a Runnable task for execution
		game.setGameState(game.getGameState().nextState());
		if (game.getGameState().equals(State.RUNNING)) {
			System.out.println("creo una nuova game");
		//TODO:	game = new Model();
			System.out.println(game.getGameState());
			controller = new Controller(game);
		}*/
}
	
	
}

