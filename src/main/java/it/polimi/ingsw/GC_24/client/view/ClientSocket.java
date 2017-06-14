package it.polimi.ingsw.GC_24.client.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class ClientSocket {

	private ViewCLI viewCLI;
	private ViewGUI viewGUI;
	private ClientSocketViewInterface clientSocketView;
	private ObjectOutputStream objToServer;
	private ObjectInputStream objFromServer;

	private final static int PORT = 28469;
	private final static String IP = "127.0.0.1";

	ExecutorService executor = Executors.newFixedThreadPool(2);

	
	public ClientSocket (){
	
		
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		ClientSocket client=new ClientSocket();
		client.startClient();
	}

	// Crea un socket e un FixedThreadPool, poi lancia un nuovo ClientInHandler
	// e un nuovo ClientOutHandler in parallelo
	public void startClient() throws IOException {
		
		Socket socket = new Socket(IP, PORT);
		System.out.println("CLIENT: Connection established");

		int viewCode = this.createInterface();
		this.createClientHandler(viewCode, socket);
		

		// Model localModel = new Model();
		// System.out.println("CLIENT: Created the local model");

	}

	

	/* Shows an Option Dialog that lets the user choose between CLI and GUI */
	public int createInterface() {
		String[] array = { "GUI", "CLI" };

		int choice =
				JOptionPane.showOptionDialog(null, "GUI or CLI?", "Choose an option", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, array, null);

		
		if (choice == 0) {
			viewGUI = new ViewGUI();
			executor.submit(viewGUI);
		} else {
			viewCLI = new ViewCLI();
			executor.submit(viewCLI);
		}

		return choice;
	
		}

	public void createClientHandler(int i, Socket socket) throws IOException {

		objToServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		objFromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		if (i == 0) {

			clientSocketView = new ClientSocketViewGUI(objFromServer, objToServer, viewGUI);

		} else {
			clientSocketView = new ClientSocketViewCLI(objFromServer, objToServer, viewCLI);

		}
		executor.submit(clientSocketView);
		
	}

}
