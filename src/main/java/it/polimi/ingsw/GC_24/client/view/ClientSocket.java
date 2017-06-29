package it.polimi.ingsw.GC_24.client.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.client.rmi.ClientRMIView;
import it.polimi.ingsw.GC_24.client.rmi.ServerViewRemote;

public class ClientSocket {

	private ViewInterface view = new ViewCLI();
	

	private final static int SOCKETPORT = 19999;
	private final static String IP = "127.0.0.1";
	private final static int RMIPORT = 28469;
	private final static String RMINAME = "remoteServer";

	ExecutorService executor = Executors.newFixedThreadPool(2);

	public ClientSocket() {
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientSocket client = new ClientSocket();
		// createAndStartsInterface();
		String network = askForSocketOrRMI();
		if (network.equalsIgnoreCase("SOC")) {
			client.startSocketClient();
		}else {
			try {
				client.startRMIClient();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static String askForSocketOrRMI() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Lorenzo il Magnifico.");
		System.out.println("Do you want to connect via socket or via RMI? Make your choice (SOC/RMI)");
		String choice = scanner.nextLine();
		while (!(choice.equalsIgnoreCase("SOC") || choice.equalsIgnoreCase("RMI"))) {
			System.out.println("Wrong choice, try again: (SOC/RMI)");
			choice = scanner.nextLine();
		}
		return choice;
	}
	
	/* Shows an Option Dialog that lets the user choose between CLI and GUI */
	public int createAndStartsInterface() {
		String[] array = { "GUI", "CLI" };

		int choice = JOptionPane.showOptionDialog(null, "GUI or CLI?", "Choose an option", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, array, null);

		if (choice == 0) {
			view = new ViewGUI();
			executor.submit(view);
		} else {
			view = new ViewCLI();
			executor.submit(view);
		}

		return choice;

	}

	// Crea un socket e un FixedThreadPool, poi lancia un nuovo ClientInHandler
	// e un nuovo ClientOutHandler in parallelo
	public void startSocketClient() throws IOException {

		Socket socket = new Socket(IP, SOCKETPORT);
		System.out.println("CLIENT: Connection established");

		
		ObjectOutputStream objToServer = new ObjectOutputStream(socket.getOutputStream());
		objToServer.flush();
		ObjectInputStream objFromServer = new ObjectInputStream(socket.getInputStream());

		ClientSocketViewCLI clientSocketView = new ClientSocketViewCLI(objFromServer, objToServer, view);

		executor.submit(clientSocketView);
		


		clientSocketView.registerMyObserver(view);
		
		//TODO: trovare un modo per sistemare casting		
		if (view instanceof ViewCLI){
		((ViewCLI) view).registerMyObserver(clientSocketView);
		}

	
		executor.submit(view);
		// TODO:sistemare quando creiamo la GUI
		// minimodel is created inside the view
	}
	
	public void startRMIClient() throws IOException, NotBoundException {

				Registry registry = LocateRegistry.getRegistry(IP, RMIPORT);
				
				ServerViewRemote serverStub = (ServerViewRemote) registry.lookup(RMINAME);

				ClientRMIView clientRMIView=new ClientRMIView(view);
				
				serverStub.registerClient(clientRMIView);
	}

	
}
