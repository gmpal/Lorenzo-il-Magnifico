package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.MyObserver;

public class RMIView extends MyObservable implements RMIViewRemote, MyObserver {

	private Set<ClientViewRemote> clients;


	public RMIView() {
		this.clients = new HashSet<>();
	}
	
	@Override
	public void registerClient(ClientViewRemote clientStub) throws RemoteException {
		System.out.println("CLIENT REGISTRATO");
		this.clients.add(clientStub);
	}


	@Override
	public void getHashMap(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(MyObservable o, C change) {
		System.out.println("SENDING THE CHANGE TO THE CLIENT");
		try {
			for (ClientViewRemote clientstub : this.clients) {
				
				clientstub.updateClient(change);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
