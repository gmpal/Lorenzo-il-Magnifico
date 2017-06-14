package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;



public interface RMIViewRemote extends Remote {

	public void registerClient(
			ClientViewRemote clientStub) 
			throws RemoteException;
	
	public void getHashMap(HashMap<String, Object> map);
}
