package it.polimi.ingsw.GC_24.client.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class ClientSocket {

	private ViewCLI viewCLI = new ViewCLI();
	private ViewGUI viewGUI;
	private ClientSocketViewInterface clientSocketView;
	private ObjectOutputStream objToServer;
	private ObjectInputStream objFromServer;

	
	private final static int PORT = 19999;
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

	//			createAndStartsInterface();

		createClientSocketView(socket);
		executor.submit(viewCLI);
		//TODO:sistemare quando creiamo la GUI
		//minimodel is created inside the view
	}

	/* Shows an Option Dialog that lets the user choose between CLI and GUI */
	public int createAndStartsInterface() {
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

	public void createClientSocketView(Socket socket) throws IOException {

		objToServer = new ObjectOutputStream(socket.getOutputStream());
		objToServer.flush();
		objFromServer = new ObjectInputStream(socket.getInputStream());

		clientSocketView = new ClientSocketViewCLI(objFromServer, objToServer, viewCLI);

		executor.submit(clientSocketView);
		
	}

}
