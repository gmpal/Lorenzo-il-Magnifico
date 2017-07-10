package it.polimi.ingsw.GC_24.network.SOC;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_24.observers.MyObservable;
import it.polimi.ingsw.GC_24.observers.MyObserver;

//There's a ServerSocketView for each Client --> SERVER SIDE
//It's this class that controls the communication from SERVER to CLIENT 
public class ServerSocketView extends MyObservable implements Runnable, MyObserver {

	private Socket socket;
	private ObjectOutputStream objToClient;
	private ObjectInputStream objFromClient;
	private boolean stop= false;

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

			while (!stop) {

				Map<String, Object> request = (Map<String, Object>) objFromClient.readObject();
				System.out.println("ServerIn: received from client: " + request);

				this.notifyMyObservers(request);
			}
		} catch (ClassNotFoundException ioe) {
			ioe.printStackTrace();
		} catch (IOException e) {
			System.out.println("Player disconnected");
			
			stop = true;
			try {
				this.socket.close();
				System.out.println("TUTTO CHIUSO");
				sendDisconnectionRequest();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

	}

	private void sendDisconnectionRequest() {
		HashMap<String, Object> disconnectionHashmap = new HashMap<>();
		disconnectionHashmap.put("clientClosed", null);
		this.notifyMyObservers(disconnectionHashmap);
	}

	@Override
	public synchronized <C> void update(C change){
		// System.out.println("ServerOut: I have been notified by " +
		// o.getClass().getSimpleName());

		try {

			objToClient.writeObject(change);
			objToClient.flush();
			objToClient.reset();

			System.out.println("ServerOut: I have sent" + change);

		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return socket;
	}

}