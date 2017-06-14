package it.polimi.ingsw.GC_24.client.rmi;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class ClientRMI {
	private final static int RMI_PORT = 29999;
	private final static String HOST = "127.0.0.1";
	private static final String NAME = "rmiView";

	public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {

		//Get the remote registry
		Registry registry = LocateRegistry.getRegistry(HOST, RMI_PORT);

		//get the stub (local object) of the remote view
		RMIViewRemote serverStub = (RMIViewRemote) registry.lookup(NAME);

		ClientRMIView rmiView=new ClientRMIView();

		// register the client view in the server side (to receive messages from the server)
		serverStub.registerClient(rmiView);
		
		
		
		
		//Riceve un input e richiama il metodo 
		//corrispondente di serverStub
		
		
		/*
		Scanner stdIn = new Scanner(System.in);

		while (true) {
			//Capture input from user
			String inputLine = stdIn.nextLine();
			System.out.println("SENDING "+inputLine);
			Action action;
			Query query;
			try {

				// Call the appropriate method in the server
				switch (inputLine) {
				case "ON":
					serverStub.turnOn();
					break;
				case "OFF":
					serverStub.turnOff();
					break;
				case "PRINT":
					serverStub.printModel();
					break;
				case "SCOMMETTI":
					serverStub.scommetti();
					break;
				
				default:
					break;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 	
		}*/
		
		
	}
}
