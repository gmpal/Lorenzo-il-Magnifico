package it.polimi.ingsw.GC_24.client.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMIView extends UnicastRemoteObject implements ClientViewRemote, Serializable {

	protected ClientRMIView() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateClient(Object obj) throws RemoteException {
	System.out.println(obj);
		
	}



}
