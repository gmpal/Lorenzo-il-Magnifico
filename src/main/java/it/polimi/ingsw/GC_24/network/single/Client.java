package it.polimi.ingsw.GC_24.network.single;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	
	public static void main(String[] args) {
	
		Client client = new Client();
		
		try {
			client.startClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static final int PORT = 28469;
	private static final String IP = "127.0.0.1";
	
	
	public void startClient() throws IOException{
		
		
		Socket socket = new Socket(IP, PORT);
		System.out.println("Connection established");
	
		Scanner socketIn = new Scanner(socket.getInputStream());
		PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
		
		//Scanner to read from standard input
		
		Scanner stdin;
		stdin = new Scanner(new InputStreamReader(System.in));
		//why not?
		//stdin = new Scanner(System.in);
		
		
		while(true){
			String inputline = stdin.nextLine();
			
			socketOut.println(inputline);
			socketOut.flush();
			
			if(socketIn.hasNextLine()){
				String socketLine = socketIn.nextLine();
				System.out.println(socketLine);
				}
			else {
				break;
			}
		}
		
		socketIn.close();
		socketOut.close();
		stdin.close();
		
		socket.close();
		
		
	}
}
