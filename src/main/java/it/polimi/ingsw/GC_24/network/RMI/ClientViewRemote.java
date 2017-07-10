package it.polimi.ingsw.GC_24.network.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;


public interface ClientViewRemote extends Remote {
	

	public abstract void show(String message) throws RemoteException;

	

	public abstract void startPlaying() throws RemoteException;
	
		
	public abstract void parsePersonalInformations(String[] personalInformations)throws RemoteException;
	
	public abstract void parseBoardInformations(String[] boardInformations)throws RemoteException;
	

	public abstract void signalCompletedAction() throws RemoteException;


	/* Questi due non si possono inglobare in sendModel()? */
	public abstract void updateTurn(ArrayList<String> turn) throws RemoteException;

	public abstract void updateCurrentPlayerAndSetMyTurn(String currentPlayer) throws RemoteException;
	
	public abstract String chooseAlternativeCost(String request) throws RemoteException;

	public abstract String chooseAlternativeSale(String increase) throws RemoteException;

	public abstract String askForCouncilPrivilege(String request) throws RemoteException;
	
	public abstract String askForExchange(String request) throws RemoteException;
	
	public abstract String askForServantsForHarvestOrProduction(String request) throws RemoteException;
	
	public abstract String askForChooseNewCard(String request) throws RemoteException;
	
	public abstract String getPlayerName() throws RemoteException;



	public abstract String askForVatican() throws RemoteException;

	public abstract void ping() throws RemoteException;

	public abstract void updateRankings(String string) throws RemoteException;
	
	public abstract void updateUrlPersonalBoard(ArrayList<String> urlPersonalBoard) throws RemoteException;
	
	public abstract void updateUrlBoard(ArrayList<String> urlBoard) throws RemoteException;
	
	public abstract void updateUrlExcommunication(ArrayList<String> urlExcommunication) throws RemoteException;



	public abstract void updateUrlColour(ArrayList<String> urlColour)  throws RemoteException;



	

}