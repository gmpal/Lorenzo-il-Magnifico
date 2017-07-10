package it.polimi.ingsw.GC_24.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;

import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.network.RMI.ServerRMIView;
import it.polimi.ingsw.GC_24.network.RMI.ServerViewRemote;
import it.polimi.ingsw.GC_24.network.SOC.ServerSocketView;

public class Server {

	// TODO: è davvero necessario il triangolo MVC se tanto alla fine poi chiamo
	// sempre game.sendModel()?
	private static final int PORT = 19999;
	private final static int RMI_PORT = 28469;

	private static ArrayList<Controller> listOfControllers = new ArrayList<>();
	
	private static Model game;
	private static Controller controller;
	private static ServerSocketView serverSocketView;
	private static ServerRMIView rmiView;
	private static Registry registry;
	private static int i = 0;
	private static int modelIndex = 1;
	private static ExecutorService threadPool = Executors.newCachedThreadPool();

	// Crea un server e fa partire il suo metodo startServer()
	public static void main(String[] args) throws RemoteException {
		Server server = new Server();

		new Thread(new Runnable() {
			public void run() {
				try {
					Server.startSocketServer();
				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					server.startRMIServer();
				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}).start();
	}

	// constructor
	public Server() throws RemoteException {
		game = new Model(modelIndex);
		System.out.println("SERVER: Model Created");
		controller = new Controller(game);
		System.out.println("SERVER: Controller Created");
	}

	private void startRMIServer() throws AlreadyBoundException, RemoteException {

		// create the registry to publish remote objects
		Registry createdRegistry = LocateRegistry.createRegistry(RMI_PORT);
		registry = createdRegistry;
		System.out.println("Constructing the RMI registry");

		// Create the RMI View, that will be shared with the client
		createRMIServerView();

	}

	private static void createRMIServerView() {
		rmiView = new ServerRMIView();

		// controller observes this view
		rmiView.registerMyObserver(controller);
		controller.registerMyObserver(rmiView);

		

		try {
			ServerViewRemote viewRemote = (ServerViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);
			registry.rebind("remoteServer", viewRemote);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Handle the clients connection adding them to the model, if it is not
	 * already full
	 */
	public static void startSocketServer() throws IOException, InterruptedException {

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");

		while (true) {
			try {
				i++;
				System.out.println("SERVER: Waiting connection number" + i);
				Socket socket = serverSocket.accept();
				System.out.println("Connection #" + i + " done");
				createServerSocketView(socket);
				registerObservers();
				

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("PLAYER DISCONNESSO, CHE SI FA?");
				break;
			}
		}
		threadPool.shutdown();
		serverSocket.close();
	}

	private static void registerObservers() {
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
		listOfControllers.add(controller);
		modelIndex++;
		threadPool.submit(controller);
		game = new Model(modelIndex);
		controller = new Controller(game);
		PlayerColour.resetValues();
		createRMIServerView();
		System.out.println("SERVER: ----> Game #" + modelIndex + " created");

	}

	public static ArrayList<Controller> getListOfControllers() {
		return listOfControllers;
	}

	public static void setListOfControllers(ArrayList<Controller> listOfControllers) {
		Server.listOfControllers = listOfControllers;
	}

	public static Controller getController() {
		return controller;
	}

}