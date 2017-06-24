package it.polimi.ingsw.GC_24.client.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends MyObservable implements Runnable, MyObserver {

	private Socket socket;
	private ObjectOutputStream objToClient;
	private ObjectInputStream objFromClient;
	private boolean end;

	// constructor --> Receive a socket and creates Scanner and PrintWriter
	public ServerSocketView(Socket socket) throws IOException {
		this.socket = socket;
		this.objToClient = new ObjectOutputStream(socket.getOutputStream());
		objToClient.flush();
		this.objFromClient = new ObjectInputStream(socket.getInputStream());
		
	}

	/**
	 * This methods keeps receiving Objects from the socket Input Stream and
	 * handling them, giving a response. If the request contains "EXIT" the
	 * connection is closed
	 */
	@Override
	public void run() {

		try {

			while (true) {
				

				Map<String, Object> request = (Map<String, Object>) objFromClient.readObject();
			//	System.out.println("ServerIn: received from client: " + request);
				this.notifyMyObservers(request);
				
			}

		} catch (ClassNotFoundException ioe) {
			ioe.printStackTrace();
		} catch (EOFException eof) {
			System.out.println("SERVERIn: end of file reached");
		} catch (IOException io) {
			io.printStackTrace();

		}

	}

	@Override
	public void update() {
		System.out.println("ServerOut: I have been notified!");

	}

	
	
	@Override
	public <C> void update(MyObservable o, C change) {
	//	System.out.println("ServerOut: I have been notified by " + o.getClass().getSimpleName());

		try {
			
			objToClient.writeObject(change);
			objToClient.flush();
			objToClient.reset();
			System.out.println("ServerOut: I have sent"+change);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}