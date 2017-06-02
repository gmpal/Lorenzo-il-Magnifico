package it.polimi.ingsw.GC_24.network.multi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.view.ViewCLI;
import it.polimi.ingsw.GC_24.view.ViewPlayer;

public class Client {

	private ViewPlayer viewPlayer;
	private ObjectOutputStream objToServer;
	private ObjectInputStream objFromServer;
	
	
	
	public static void main(String[] args) {
		Client client = new Client();
		
		try {
			client.startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final static int PORT = 28469;
	private final static String IP = "127.0.0.1";

	// Crea un socket e un FixedThreadPool, poi lancia un nuovo ClientInHandler
	// e un nuovo ClientOutHandler in parallelo
	public void startClient() throws IOException {

		Socket socket = new Socket(IP, PORT);
		System.out.println("Connection established");

		
		/*THIS BLOCK OF CODE CREATES THE ViewPlayer!*/
		/*n=0 --> GUI ; n=1 --> CLI*/
	//	int n = this.selectInterface();
	//	if (n==0){
	//		//viewPlayer = new ViewGUI();
	//	}else{
			 viewPlayer = new ViewCLI();
	//	}
		System.out.println("Created the view");
		
		
		/*Creating an Executor Service in order to run the ClientIN and ClientOut
		 * simoultaneously*/
		ExecutorService executor = Executors.newFixedThreadPool(2);
	
		//creates the handlers
		executor.submit(new ClientInHandler(new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))));
		System.out.println("Created the InHandler");
		ClientOutHandler cientOutHandler = new ClientOutHandler(new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream())));
		System.out.println("Created the OutHandler");
		Model localModel = new Model();
		System.out.println("Created the model");
		
		
		//set Observers in Client
		viewPlayer.registerMyObserver(cientOutHandler);
		cientInHandler.registerMyObserver(viewPlayer);
		System.out.println("Setted the observers");
		viewPlayer.start();
		
	}

	
/*Shows an Option Dialog that lets the user choose between CLI and GUI*/	
	public static int selectInterface(){

		String[] array = {"GUI","CLI"};

		int choice = JOptionPane.showOptionDialog(
			 null,
			 "GUI or CLI?",
			 "Choose an option",
    		 JOptionPane.YES_NO_OPTION, 
    		 JOptionPane.QUESTION_MESSAGE,
    		 null,array,null); 
     
     return choice;
}

}
