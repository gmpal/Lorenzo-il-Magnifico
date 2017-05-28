package it.polimi.ingsw.GC_24.network.single;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) {
		Server echoServer = new Server();
		
		try {
			echoServer.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private final static int PORT = 28469;
	
	public void startServer() throws IOException{
		
		//Creating a new Server socket on the specified port
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server ready on PORT: "+PORT);
		
		
		
		//Puts the serverSocket in waiting for client connection
		Socket socket = serverSocket.accept();
		System.out.println("Connection received");
		
		
		Scanner socketIn = new Scanner(socket.getInputStream());
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
		
		
		while(true){
			String line = socketIn.nextLine();
			System.out.println("SERVER: received string --> "+line);
			if (line.equals("quit")){
				break;
			}
			else{
				socketOut.println("Well done Client, Server has received --->"+line );
				socketOut.flush();
			}
		}
		
		System.out.println("Closing the scanners");
		socketIn.close();
		socketOut.close();
		
		System.out.println("Closing the socket");
		socket.close();
		serverSocket.close();
	}
}
