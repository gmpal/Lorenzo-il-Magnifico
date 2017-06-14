package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ClientViewRemote extends Remote {
	public void updateClient(Object obj) 
			throws RemoteException;
}
