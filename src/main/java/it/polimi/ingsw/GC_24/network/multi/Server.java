package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;
import it.polimi.ingsw.GC_24.client.rmi.RMIView;
import it.polimi.ingsw.GC_24.client.rmi.RMIViewRemote;
import it.polimi.ingsw.GC_24.client.view.ServerSocketView;
import it.polimi.ingsw.GC_24.controller.Controller;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.PlayerColour;
import it.polimi.ingsw.GC_24.model.State;

public class Server {

	private static final int PORT = 28469;
	private final static int RMI_PORT = 29999;
	private final static int PLAYER_NUMBER = 4;
	private final String NAME = "rmiView";
	private static Model game;
	private static Controller controller;
	private ExecutorService threadPool = Executors.newCachedThreadPool();
	private static ServerSocketView serverSocketView;
	private static Player player;
	private static Timer timer;
	private int i = 0;
	private int modelIndex = 1;
  
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
		RMIViewRemote viewRemote = (RMIViewRemote) UnicastRemoteObject.exportObject(rmiView, 0);

		System.out.println("Binding the server implementation to the registry");
		registry.bind(NAME, rmiView);

	}

	// Crea un Cached Thread Pool e un serverSocket , poi si mette in attesa dei
	// socket
	// Quando un client si connette crea un ClientHandler e lo fa partire
	public void startSocketServer() throws IOException, InterruptedException {
		timer = new Timer();

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("SERVER: ServerSocket created");

		while (true) {
			try {
				i++;
				System.out.println("SERVER: Waiting connection number" + i);
				Socket socket = serverSocket.accept();
				serverSocketView = new ServerSocketView(socket);
				threadPool.submit(serverSocketView);
				System.out.println("SERVER: ServerSocketView created");

				registerObserver();
				sendNumberToClient();
				player = new Player();
				game.getPlayers().add(player);
				System.out.println("PLAYER " + player);
				System.out.println("Player #" + i + "added to Game #" + game.getModelNumber());
				game.incrementState();

				game.sendModel();

				if (game.getGameState().equals(State.WAITINGFORPLAYERTHREE)) {
					System.out.println("Timer Starting");
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							System.out.println("************************TIME'S UP*****************************");
							controller.autoCompletePlayers();
							launchAndCreateNewGame();
						}
					}, 15000);
				}

				if (game.getGameState().equals(State.RUNNING)) {
					timer.cancel();
					// TODO: il quarto giocatore non ha tempo di inserire il
					// nome
					System.out.println("TIMER CANCELED");
					controller.autoCompletePlayers();
					launchAndCreateNewGame();

					if (game.getGameState().equals(State.RUNNING)) {
						while (game.getPlayers().get(PLAYER_NUMBER - 1).getMyName().equals("TempName")) {
							System.out.printf("");
							// just waits untils the last player is
							// automatically/manually created
						}
						System.out.println("TIMER CANCELED");
						timer.cancel();

						controller.autoCompletePlayers();
						launchAndCreateNewGame();

					}

					System.out.println("SERVER: THE ACTUAL GAME IS " + game.getModelNumber());
					System.out.println("SERVER: THE ACTUAL STATE IS " + game.getGameState());
					System.out.println("SERVER: THE ACTUAL PLAYERS ARE " + game.getPlayers());

				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		threadPool.shutdown();
		serverSocket.close();
	}

	private void sendNumberToClient() throws IOException {
		HashMap<String, Object> hm = new HashMap<>();
		hm.put("clientNumber", i);
		serverSocketView.sendToClient(hm);

	}

	private void launchAndCreateNewGame() {
		modelIndex++;
		threadPool.submit(this.controller);
		this.game = new Model(modelIndex);
		this.controller = new Controller(game);
		PlayerColour.resetValues();
		System.out.println("Game #" + modelIndex + " created");

	}

	/*
	 * public static void tryToCreateANewGame() throws InterruptedException {
	 * 
	 * System.out.println("Starting Timer because of second player created");
	 * Timer.startTimer(15);
	 * 
	 * game.setModel(game.getPlayers()); game.sendModel(); newGame();
	 * 
	 * }
	 * 
	 * public static void createANewGame() {
	 * System.out.println("Creating a new game because of four players created"
	 * ); game.setModel(game.getPlayers()); game.sendModel(); newGame();
	 * 
	 * }
	 * 
	 * private static void newGame() { PlayerColour.resetValues(); game = new
	 * Model(); controller = new Controller(game);
	 * System.out.println("NEW GAME CREATED"); }
	 */
	public void addClient(Socket socket) throws IOException {

	}

	public static void registerObserver() {
		game.registerMyObserver(serverSocketView);
		serverSocketView.registerMyObserver(controller);
		controller.registerMyObserver(serverSocketView);
	}

	public static void removeObserverFromThis(MyObservable o) {
		game.unregisterMyObserver((MyObserver) o);
		o.unregisterMyObserver(controller);
		controller.unregisterMyObserver((MyObserver) o);
	}

	public static void registerObserverToThis(MyObservable o) {
		game.registerMyObserver((MyObserver) o);
		o.registerMyObserver(controller);
		controller.registerMyObserver((MyObserver) o);

	}

}