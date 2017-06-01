package it.polimi.ingsw.GC_24.network.multi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

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

		/*n=0 --> GUI ; n=1 --> CLI*/
		int n = selectInterface();
		if (n==0){
			//viewPlayer = new ViewGUI();
		}else{
			 viewPlayer = new ViewCLI();
		}
		viewPlayer.start();
				
		objToServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		objFromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		
		/**The clientInHandler needs to start since it's listening for everything from
		 * the socketOutputStream, while the clientOutHandler starts when is notified by the 
		 * PlayerView*/
		
		
		new ClientInHandler(objFromServer).start();
		new ClientOutHandler(objToServer);
	}

	
/*Shows an Option Dialog that lets the user choose between CLI and GUI*/	
	public static int selectInterface(){

		String[] array = {"GUI","CLI"};

		int choice = JOptionPane.showOptionDialog(null,
			 "GUI or CLI?",
			 "Choose an option",
    		 JOptionPane.YES_NO_OPTION, 
    		 JOptionPane.QUESTION_MESSAGE,
    		 null,array,null); 
     
     return choice;
}

}
