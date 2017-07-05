package it.polimi.ingsw.GC_24.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import it.polimi.ingsw.GC_24.network.RMI.ClientRMIView;
import it.polimi.ingsw.GC_24.network.RMI.ClientViewRemote;
import it.polimi.ingsw.GC_24.network.RMI.ServerViewRemote;
import it.polimi.ingsw.GC_24.network.SOC.ClientSocketView;
import it.polimi.ingsw.GC_24.view.View;
import it.polimi.ingsw.GC_24.view.ViewCLI;
import it.polimi.ingsw.GC_24.view.ViewGUI;


public class Client {

	private final static int SOCKETPORT = 19999;
	private final static String IP = "127.0.0.1";
	private final static int RMIPORT = 28469;
	private final static String RMINAME = "remoteServer";

	ExecutorService executor = Executors.newFixedThreadPool(2);



	

	// Crea un socket e un FixedThreadPool, poi lancia un nuovo ClientInHandler
	// e un nuovo ClientOutHandler in parallelo
	public void startSocketClient(View view) throws IOException {

		Socket socket = new Socket(IP, SOCKETPORT);
		System.out.println("CLIENT: Connection established");

		ObjectOutputStream objToServer = new ObjectOutputStream(socket.getOutputStream());
		objToServer.flush();
		ObjectInputStream objFromServer = new ObjectInputStream(socket.getInputStream());

		ClientSocketView clientSocketView = new ClientSocketView(objFromServer, objToServer, view);

		executor.submit(clientSocketView);

		clientSocketView.registerMyObserver(view);

		view.registerMyObserver(clientSocketView);
		// TODO:sistemare quando creiamo la GUI
		// minimodel is created inside the view
	}

	public void startRMIClient(View view) throws IOException, NotBoundException {

		Registry registry = LocateRegistry.getRegistry(IP, RMIPORT);

		ServerViewRemote serverStub = (ServerViewRemote) registry.lookup(RMINAME);

		ClientRMIView clientRMIView = new ClientRMIView(serverStub, view);

		@SuppressWarnings("unused")
		ClientViewRemote viewRemote = (ClientViewRemote) UnicastRemoteObject.exportObject(clientRMIView, 0);

		serverStub.registerClient(viewRemote);

		view.registerMyObserver(clientRMIView);
	}

}
