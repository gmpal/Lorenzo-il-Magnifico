package it.polimi.ingsw.GC_24.client.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;

import it.polimi.ingsw.GC_24.MyObservable;
import it.polimi.ingsw.GC_24.client.view.ViewInterface;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.Model;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.SetOfValues;
import it.polimi.ingsw.GC_24.values.Value;

public class ClientRMIView extends MyObservable implements ClientViewRemote {
	
	
	private ViewInterface view;

	public ClientRMIView(ViewInterface view) throws RemoteException {
		this.view = view;
		}



	@Override
	public void show(String message) throws RemoteException {
		System.out.println("RMI Client View -->");
		view.show(message);
		
	}


	@Override
	public void updateModelAndRelatedFields(Model model) throws RemoteException {
		System.out.println("RMI Client View --> updateModelAndRelatedFields ");
		view.getInformationForReceivedModel(model);
		
	}


	@Override
	public void signalCompletedAction() throws RemoteException {
		System.out.println("RMI Client View --> signalCompletedAction ");
		view.communicateActionDone();
		
	}

	@Override
	public void startPlaying() throws RemoteException {
		System.out.println("RMI Client View --> startPlaying ");
		view.play();
	}

	@Override
	public void updateTurn(ArrayList<Player> turn) throws RemoteException {
		System.out.println("RMI Client View --> updateTurn ");
		view.updateTurn(turn);
	}

	@Override
	public void updateCurrentPlayerAndSetMyTurn(Player currentPlayer) throws RemoteException {
		System.out.println("RMI Client View --> updateCurrentPlayerAndSetMyTurn");
		view.setMyTurn(currentPlayer);
	}

	@Override
	public void setPlayerNumber(int playerNumber, int modelNumber) throws RemoteException {
		System.out.println("RMI Client View --> setPlayerNumber ");
		view.updatePlayerNumber(playerNumber, modelNumber);
		
	}



	@Override
	public String chooseAlternativeCost(String request) throws RemoteException {
		System.out.println("RMI Client View --> ChooseAlternativeCost ");
		String response = view.chooseAlternativeCost(request);
		return response;
	}



	@Override
	public SetOfValues chooseAlternativeSale(IncreaseDieValueCard increase) throws RemoteException {
		System.out.println("RMI Client View --> ChooseAlternativeSale ");
		SetOfValues response = view.chooseSale(increase);
		return response;
	}



	@Override
	public String askForCouncilPrivilege(String request) {
		System.out.println("RMI Client View --> askForCouncilPrivilege ");
		String response = view.askForCouncilPrivilege(request);
		return response;
	}



	@Override
	public String askForExchange(String request) {
		System.out.println("RMI Client View --> askForExchange ");
		String response = view.askForExchange(request);
		return response;
	}



	@Override
	public String askForServantsForHarvestOrProduction(String request) {
		System.out.println("RMI Client View --> askForServantsForHarvestOrProduction ");
		String response = view.askForServantsForHarvestOrProduction(request);
		return response;
	}



	@Override
	public String askForChooseNewCard(String request) {
		System.out.println("RMI Client View --> askForChooseNewCard ");
		String response = view.askForChooseNewCard(request);
		return response;
	}



	@Override
	public String getPlayerName() throws RemoteException {
		return view.getName();
	}

	

}
