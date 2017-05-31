package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;



//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends MyObservable implements MyObserver, Runnable{

	private Socket socket;
	private Scanner stringFromClient;
	private PrintWriter stringToClient;
	private ObjectOutputStream objToClient;
	private ObjectInputStream objFromClient;
	
	
	//constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerSocketView(Socket socket) throws IOException{
		this.socket=socket;
		
		stringFromClient = new Scanner(socket.getInputStream());
		stringToClient = new PrintWriter (socket.getOutputStream());
		objToClient = new ObjectOutputStream(socket.getOutputStream());
		objFromClient = new ObjectInputStream(socket.getInputStream());
	}
	
	

	@Override
	public void run() {
		
		//receive the name and the colour from the client
		String nameAndColour = stringFromClient.nextLine();
		
			while(true){
				String line = stringFromClient.nextLine();
				
				
				
				System.out.println("Server: getting the command "+line);
				
				//TOKENIZES THE LINE (CUTS IT IN PIECES)
				StringTokenizer tokenizer = new StringTokenizer((String) line);
				
				
				//TODO: decidere come passare il comando
				/*This block of codes assign the pieces of the command line 
				 * to the values --> IT CREATES (!) a new Player from the passed name
				 * and creates an action */
			}
		
	}



	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		// TODO Auto-generated method stub
		
	}

}

