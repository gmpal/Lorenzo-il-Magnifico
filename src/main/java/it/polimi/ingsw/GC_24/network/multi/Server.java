package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.client.rmi.RMIView;
import it.polimi.ingsw.GC_24.client.rmi.RMIViewRemote;
import it.polimi.ingsw.GC_24.client.view.ServerSocketView;
import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;


public class Server {

	private static final int PORT = 28469;
	private final static int RMI_PORT = 29999;
	private final String NAME = "rmiView";
	private List<Player> players;
	private Model game;
	private Controller controller;
	
	// Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) throws IOException, AlreadyBoundException {
		Server server = new Server();
		server.startSocketServer();
		server.startRMI();

		
			
		
	}

	// constructor
	public Server() throws RemoteException {
		game = new Model();
		System.out.println("SERVER: Model Created");
		controller = new Controller(game);
		System.out.println("SERVER: Controller Created");
	}

	
private void startRMI() throws RemoteException, AlreadyBoundException{

		//create the registry to publish remote objects
		Registry registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		RMIView rmiView=new RMIView();

		//controller observes this view
		rmiView.registerMyObserver(this.controller);

		//this view observes the model
		this.game.registerMyObserver(rmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote=(RMIViewRemote) UnicastRemoteObject.
				exportObject(rmiView, 0);
		
		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, rmiView);

		
	}	
	
	// Crea un Cached Thread Pool e un serverSocket , poi si mette in attesa dei
	// socket
	// Quando un client si connette crea un ClientHandler e lo fa partire
	public void startSocketServer() throws IOException {
		
		ExecutorService threadPool = Executors.newCachedThreadPool();
		System.out.println("SERVER: ThreadPool Created");
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");	
		
		System.out.println("SERVER: ready");
		
	
		
		int clientNumber = 0;
		while(true){
			try {
				Socket socket = serverSocket.accept();
				clientNumber++;
				System.out.println("SERVER: client num. "+clientNumber+" connected");
		
				ServerSocketView serverSocketView = new ServerSocketView(socket);
				threadPool.submit(serverSocketView);
				
				System.out.println("SERVER: ServerIn created");
				
				game.registerMyObserver(serverSocketView);
				serverSocketView.registerMyObserver(controller);
				controller.registerMyObserver(serverSocketView);
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
	/*public void addClient(ServerOut serverOut) {
		game.registerMyObserver(serverOut);
		// Submits a Runnable task for execution
		game.setGameState(game.getGameState().nextState());
		if (game.getGameState().equals(State.RUNNING)) {
			System.out.println("creo una nuova game");
		//TODO:	game = new Model();
			System.out.println(game.getGameState());
			controller = new Controller(game);
		}*/
}
	
	


