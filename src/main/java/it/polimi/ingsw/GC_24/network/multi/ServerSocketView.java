package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.view.View;


//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends View implements Runnable{

	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	
	//constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerSocketView(Socket socket) throws IOException{
		this.socket=socket;
		
		socketIn = new Scanner(socket.getInputStream());
		
		socketOut = new PrintWriter (socket.getOutputStream());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		
			while(true){
				String line = socketIn.nextLine();
				
				System.out.println("Server: getting the command "+line);
				
				//TOKENIZES THE LINE (CUTS IT IN PIECES)
				StringTokenizer tokenizer = new StringTokenizer((String) line);
				
				
				//TODO: decidere come passare il comando
				/*This block of codes assign the pieces of the command line 
				 * to the values --> IT CREATES (!) a new Player from the passed name
				 * and creates an action */
			}
		
	}

}

