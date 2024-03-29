package it.polimi.ingsw.GC_24.network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerViewRemote extends Remote {

	public abstract void registerClient(ClientViewRemote clientStub) throws RemoteException;
		
	public abstract void sendPlayerString(String name) throws RemoteException;

	public abstract void sendAction(String action) throws RemoteException;
	
	public abstract void sendAlternativeCost(String answer) throws RemoteException;
	
	public abstract void sendAnswerForParameters(String answer) throws RemoteException;

	public abstract void sendAlternativeSale(String alternativeSale) throws RemoteException ;
	
	public abstract void sendLeader(String request) throws RemoteException;
	
	public abstract void sendAnswerVatican(String request) throws RemoteException;
}