package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/*Un nuovo oggetto di Clase ClientHandler è creato ogni volta che un client
 * si connette ad un server e gli viene passato il suo socket 
 * QUANDO PARTE UN CLIENT crea uno scanner e un printwriter per leggere e scrivere da e su socket
 * per tutta la sua durata legge ciò che arriva e lo stampa su schermo se è diverso da quit.
 * dicendoti che il server lo ha ricevuto 
 * --> UN CLIENT HANDLER PER OGNI CLIENT CHE SI CONNETTE, SONO I DIVERSI HANDLER CHE AGISCONO IN PARALLELO */
public class ClientHandler implements Runnable {

	private Socket socket;
	
	public ClientHandler(Socket socket){
		this.socket=socket;
	}
	
	
	
	@Override
	public void run() {
		
		try {
			Scanner socketIn = new Scanner(socket.getInputStream());
			PrintWriter socketOut = new PrintWriter (socket.getOutputStream());
			
			while(true){
				String line = socketIn.nextLine();
				System.out.println(line);
				
				if (line.equals("quit")){
					break;
				}
				else {
					socketOut.println("Well done client, the server has reveived the String: " + line);
					socketOut.flush();
				}
			}
			
			socketIn.close();
			socketOut.close();
			socket.close();
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
