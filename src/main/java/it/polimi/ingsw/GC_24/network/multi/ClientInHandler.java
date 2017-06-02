package it.polimi.ingsw.GC_24.network.multi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.values.SetOfValues;


//ClientInHandler is observed by the ViewPLayer,
//whenever the server communicates something, ClientInHandler notifies ViewPLayer
public class ClientInHandler extends MyObservable{

	private ObjectInputStream objFromServer;
	private boolean end = false;

	public ClientInHandler(ObjectInputStream objFromServer) {
		this.objFromServer = objFromServer;
	}

	public void start() {
		while (!end) {
			
			Map<String, Object> requestFromServer;
			try {
				requestFromServer = (Map<String, Object>) objFromServer.readObject();
				this.handleRequestFromServer(requestFromServer);
			
			} catch (ClassNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
			
		}
	}
	/**Based on the key of the object received, this method handles the request*/
	private void handleRequestFromServer(Map<String, Object> request) {
		Set<String> command = request.keySet();
		
		if (command.contains("TEST")){
			 SetOfValues set = (SetOfValues) request.get("TEST");
			 System.out.println("ClientIn: preso oggetto test");
			 System.out.println(set.toString());
		}
	}

}
