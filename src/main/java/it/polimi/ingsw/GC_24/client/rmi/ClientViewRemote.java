package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;


public interface ClientViewRemote extends Remote {
	

	public abstract void show(String message) throws RemoteException;

	

	public abstract void startPlaying() throws RemoteException;
	
	public abstract void setPlayerNumber(int playerNumber, int modelNumber) throws RemoteException;
	
	public abstract void updateModelAndRelatedFields(Model model) throws RemoteException;

	public abstract void signalCompletedAction() throws RemoteException;


	/* Questi due non si possono inglobare in sendModel()? */
	public abstract void updateTurn(ArrayList<Player> turn) throws RemoteException;

	public abstract void updateCurrentPlayerAndSetMyTurn(Player currentPlayer) throws RemoteException;
	
	public abstract String chooseAlternativeCost(String request) throws RemoteException;

	public abstract SetOfValues chooseAlternativeSale(IncreaseDieValueCard increase) throws RemoteException;

	public abstract String askForCouncilPrivilege(String request) throws RemoteException;
	
	public abstract String askForExchange(String request) throws RemoteException;
	
	public abstract String askForServantsForHarvestOrProduction(String request) throws RemoteException;
	
	public abstract String askForChooseNewCard(String request) throws RemoteException;
	
	public abstract String getPlayerName() throws RemoteException;

}