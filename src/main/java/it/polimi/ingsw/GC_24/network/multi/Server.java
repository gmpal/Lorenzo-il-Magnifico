package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.State;
import it.polimi.ingsw.GC_24.view.View;

public class Server {

	private static final int PORT = 28469;
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
		//TODO: game = new Model();
		controller = new Controller(game);
	}

	// private static final String IP = "127.0.0.1";

	// Crea un Cached Thread Pool e un serverSocket , poi si mette in attesa dei
	// socket
	// Quando un client si connette crea un ClientHandler e lo fa partire
	public void startServer() throws IOException {
		
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server ready");
		
		while(true){
			try {
				Socket socket = serverSocket.accept();
				ServerSocketView serverView = new ServerSocketView(socket);
				this.addClient(serverView);
				threadPool.submit(serverView);
			} catch (IOException e) {
				break;
			}
		}
		
		threadPool.shutdown();
		serverSocket.close();
	}

	public void addClient(View view) {
		view.addObserver(controller);
		game.addObserver(view);
		// Submits a Runnable task for execution
		game.setGameState(game.getGameState().nextState());
		if (game.getGameState().equals(State.RUNNING)) {
			System.out.println("creo una nuova game");
		//TODO:	game = new Model();
			System.out.println(game.getGameState());
			controller = new Controller(game);
		}
}
	
	
}

