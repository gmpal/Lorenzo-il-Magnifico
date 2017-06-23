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


public class Server {

	private static final int PORT = 28469;
	private final static int RMI_PORT = 29999;
	
	private static Model game;
	private static Controller controller;
	private static ServerSocketView serverSocketView;
	

	private static int i = 0;
	private static int modelIndex = 1;
	private static ExecutorService threadPool = Executors.newCachedThreadPool();
	
	
	// Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) throws IOException, AlreadyBoundException, InterruptedException {
		Server server = new Server();
		server.startSocketServer();
		server.startRMI();
	}

	// constructor
	public Server() throws RemoteException {
		game = new Model(modelIndex);
		System.out.println("SERVER: Model Created");
		controller = new Controller(game);
		System.out.println("SERVER: Controller Created");
	}

	/**Handle the clients connection adding them to the model, if it is not already full*/
	public static void startSocketServer() throws IOException, InterruptedException {
		
	
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");

		////Quick new Thread for running the addPlayer method separately
		
		
		
		while (true) {
			try {
				i++;
				System.out.println("SERVER: Waiting connection number" + i);
				
				
					Socket socket = serverSocket.accept();
					System.out.println("Connection #" +i + " done");
					createServerSocketView(socket);
					registerObservers();
								
					game.addPlayer();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		threadPool.shutdown();
		serverSocket.close();
	}
	
	private static void registerObservers() {
		game.registerMyObserver(serverSocketView);
		serverSocketView.registerMyObserver(controller);
		controller.registerMyObserver(serverSocketView);
	}
	

	private static void createServerSocketView(Socket socket) {
		try {
			serverSocketView = new ServerSocketView(socket);
			threadPool.submit(serverSocketView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void launchAndCreateNewGame() {

		modelIndex++;
		threadPool.submit(controller);
		game = new Model(modelIndex);
		controller = new Controller(game);
		PlayerColour.resetValues();
		System.out.println("SERVER: ----> Game #"+modelIndex+" created");

	}

	
	private void startRMI() throws RemoteException, AlreadyBoundException {

		// create the registry to publish remote objects
		Registry registry = LocateRegistry.createRegistry(RMI_PORT);
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		RMIView rmiView = new RMIView();

		// controller observes this view
		rmiView.registerMyObserver(controller);

		// this view observes the model
		this.game.registerMyObserver(rmiView);

		// publish the view in the registry as a remote object
		@SuppressWarnings("unused")
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);

		System.out.println("Binding the server implementation to the registry");
		registry.bind("rmiView", rmiView);

	}
}