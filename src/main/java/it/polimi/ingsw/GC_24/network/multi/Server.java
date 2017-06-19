package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import it.polimi.ingsw.GC_24.client.rmi.RMIView;
import it.polimi.ingsw.GC_24.client.rmi.RMIViewRemote;
import it.polimi.ingsw.GC_24.client.view.ServerSocketView;
import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.Timer;
import it.polimi.ingsw.GC_24.model.State;

public class Server {

	private static final int PORT = 28469;
	private final static int RMI_PORT = 29999;
	private final String NAME = "rmiView";
	private static Model game;
	private static Controller controller;
	private ExecutorService threadPool = Executors.newCachedThreadPool();

	// Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) throws IOException, AlreadyBoundException, InterruptedException {
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

	private void startRMI() throws RemoteException, AlreadyBoundException {

		// create the registry to publish remote objects
		Registry registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		RMIView rmiView = new RMIView();

		// controller observes this view
		rmiView.registerMyObserver(this.controller);

		// this view observes the model
		this.game.registerMyObserver(rmiView);

		// publish the view in the registry as a remote object
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);

		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, rmiView);

	}

	// Crea un Cached Thread Pool e un serverSocket , poi si mette in attesa dei
	// socket
	// Quando un client si connette crea un ClientHandler e lo fa partire
	public void startSocketServer() throws IOException, InterruptedException {

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");
		game.setGameState(State.WAITINGFORPLAYERONE);
		System.out.println(game.getGameState());
		int i=0;
		while (true) {
			try {
				i++;
				System.out.println("GIRO NUMERO" +i);
				Socket socket = serverSocket.accept();
				System.out.println("************** NEW CLIENT CONNECTED");
				this.addClient(socket);
				System.out.println("************** NEW CLIENT ADDED TO GAME");
				System.out.println("************** THE ACTUAL STATE IS "+ game.getGameState()); 
			} catch (IOException e) {
				break;
			}
		}
		threadPool.shutdown();
		serverSocket.close();
	}

	public static void tryToCreateANewGame() throws InterruptedException {
		if (controller.getGame().getGameState().equals(State.WAITINGFORPLAYERTHREE)) {
			System.out.println("Starting Timer because of second player created");
			Timer.startTimer(15);
			game.setModel(controller.getGame().getPlayers());
			newGame();
		}
		if (controller.getGame().getGameState().equals(State.RUNNING)) {
			System.out.println("Creating a new game because of four players created");
			game.setModel(controller.getGame().getPlayers());
			newGame();
		}		
	}

	public static void createANewGame() {
		newGame();
		
	}

	private static void newGame() {
		PlayerColour.resetValues();
		game = new Model();
		controller = new Controller(game);
		System.out.println("NEW GAME CREATED");
	}

	public void addClient(Socket socket) throws IOException {

		ServerSocketView serverSocketView = new ServerSocketView(socket);
		threadPool.submit(serverSocketView);
//TODO: finché non inserisco il nome non super questa istruzione
		System.out.println("SERVER: ServerSocketView created");

		game.registerMyObserver(serverSocketView);
		serverSocketView.registerMyObserver(controller);
		controller.registerMyObserver(serverSocketView);

		System.out.println("SERVER: observers setted!");

	}

}