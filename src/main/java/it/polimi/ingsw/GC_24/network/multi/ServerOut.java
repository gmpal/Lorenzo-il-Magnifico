package it.polimi.ingsw.GC_24.network.multi;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

public class ServerOut implements MyObserver {

	private Socket socket;
	private ObjectOutputStream objToClient;

	public ServerOut(Socket socket) throws IOException {

		this.socket = socket;
		objToClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		System.out.println("ServerOut: OutputStream created");
		objToClient.flush();
		System.out.println("ServerOut: FLUSHED");
		
	}

	@Override
	public void update() {
		System.out.println("ServerOut: I have been notified!");

	}

	@Override
	public <O extends MyObservable, C> void update(O observed, C change) {
		System.out.println("ServerOut: I have been notified by " +observed.getClass().getSimpleName());
		
		try {
			objToClient.writeObject(change);
			objToClient.flush();
			System.out.println("ServerOut: I have sent the change");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
